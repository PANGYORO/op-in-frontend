package dev.opin.opinbackend.auth.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import dev.opin.opinbackend.auth.mapper.RepoMapper;
import dev.opin.opinbackend.auth.model.RepositoryDto;
import dev.opin.opinbackend.batch.dto.github.ContributorDto;
import dev.opin.opinbackend.constant.GitHub;
import dev.opin.opinbackend.persistence.entity.GithubContributor;
import dev.opin.opinbackend.persistence.entity.Member;
import dev.opin.opinbackend.persistence.entity.MemberTechLanguage;
import dev.opin.opinbackend.persistence.entity.Repository;
import dev.opin.opinbackend.persistence.entity.RepositoryContributor;
import dev.opin.opinbackend.persistence.entity.RepositoryGithubContributor;
import dev.opin.opinbackend.persistence.entity.RepositoryTechLanguage;
import dev.opin.opinbackend.persistence.entity.TechLanguage;
import dev.opin.opinbackend.persistence.repository.GithubContributorRepository;
import dev.opin.opinbackend.persistence.repository.MemberRepository;
import dev.opin.opinbackend.persistence.repository.MemberTechLanguageRepository;
import dev.opin.opinbackend.persistence.repository.RepoContributorRepository;
import dev.opin.opinbackend.persistence.repository.RepoRepository;
import dev.opin.opinbackend.persistence.repository.RepoTechLanguageRepository;
import dev.opin.opinbackend.persistence.repository.RepositoryGithubContributorRepository;
import dev.opin.opinbackend.persistence.repository.TechLanguageRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AsyncService {

	RepoRepository repoRepository;
	TechLanguageRepository techLanguageRepository;
	RepoTechLanguageRepository repoTechLanguageRepository;
	MemberTechLanguageRepository memberTechLanguageRepository;
	MemberRepository memberRepository;
	RepoContributorRepository repoContributorRepository;
	GithubContributorRepository githubContributorRepository;
	RepositoryGithubContributorRepository repositoryGithubContributorRepository;

	public AsyncService(RepoRepository repoRepository,
		TechLanguageRepository techLanguageRepository,
		RepoTechLanguageRepository repoTechLanguageRepository,
		MemberTechLanguageRepository memberTechLanguageRepository,
		MemberRepository memberRepository,
		RepoContributorRepository repoContributorRepository,
		GithubContributorRepository githubContributorRepository,
		RepositoryGithubContributorRepository repositoryGithubContributorRepository) {
		this.repoRepository = repoRepository;
		this.techLanguageRepository = techLanguageRepository;
		this.repoTechLanguageRepository = repoTechLanguageRepository;
		this.memberTechLanguageRepository = memberTechLanguageRepository;
		this.memberRepository = memberRepository;
		this.repoContributorRepository = repoContributorRepository;
		this.githubContributorRepository = githubContributorRepository;
		this.repositoryGithubContributorRepository = repositoryGithubContributorRepository;
	}

	@Async
	public CompletableFuture<String> process(final Member member) {
		try {
			RepositoryDto[] dtos = getMemberRepository(member.getGithubToken(), member.getGithubUserName());
			for (RepositoryDto repo : dtos) {

				try {
					Repository repository = RepoMapper.toRepository(repo, member);
					repoRepository.save(repository);
					log.info("[SUCCESS] github 유저: {} || repository update: {}",
						member.getGithubId(), repository.getId());

					// tech language 가져오기
					getMemberTechLanguage(member.getGithubToken(), repository, member);
					// contribute 가져오기
					getMemberContribute(member.getGithubToken(), repository, member);
					//원래 github contribute에 있었는지 확인, 있었으면 바꾸기

				} catch (Exception e) {
					log.error("[FAILED] github 유저: {} || repository update: {}", member.getGithubId(), repo.getId());
				}
			}
			getGithubContribute(member);
			return CompletableFuture.completedFuture("SUCCESS");
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		}
		return CompletableFuture.completedFuture("FAILED");
	}

	public static RepositoryDto[] getMemberRepository(String githubToken, String githubUserName) {
		ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
			.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // 10MB
			.build();

		WebClient webClient = WebClient.builder()
			.exchangeStrategies(exchangeStrategies)
			.build();

		return webClient
			.get()
			.uri(GitHub.getUserRepoUrl(githubUserName, "1"))
			.header("Authorization", "token " + githubToken)
			.retrieve().bodyToMono(RepositoryDto[].class).block();
	}

	public void getMemberTechLanguage(String githubToken, Repository repo, Member member) {
		int page = 1;
		while (true) {
			Map<String, Long> languages = getRepositoryLanguages(githubToken, repo.getFullName(),
				String.valueOf(page));

			for (String lan : languages.keySet()) {
				TechLanguage techLanguage = techLanguageRepository.findByTitle(lan)
					.orElse(null);

				if (techLanguage == null) {
					techLanguage = techLanguageRepository.save(
						TechLanguage.builder().title(lan).build());
				}

				RepositoryTechLanguage repoTechRelation = repoTechLanguageRepository.findByRepositoryAndTechLanguage(
					repo, techLanguage).orElse(null);
				MemberTechLanguage memberTechLanguage = memberTechLanguageRepository.findByMemberAndTechLanguage(member,
					techLanguage).orElse(null);

				if (repoTechRelation == null) {
					repoTechLanguageRepository.save(RepositoryTechLanguage.builder()
						.techLanguage(techLanguage)
						.repository(repo)
						.build());
				}

				if (memberTechLanguage == null) {
					memberTechLanguageRepository.save(
						MemberTechLanguage.builder()
							.member(member)
							.techLanguage(techLanguage)
							.build()
					);

				}
			}

			if (languages.size() < 100) {
				break;
			}

			page += 1;

		}
	}

	public void getMemberContribute(String githubToken, Repository repo, Member me) {
		int page = 1;

		while (true) {
			// 먼저 자기 레포의 contributor 가져오고,
			ContributorDto[] contributorDtos = getContributors(githubToken, repo.getFullName(),
				String.valueOf(page));

			for (ContributorDto contributor : contributorDtos) {
				// contributor 가 우리 멤버인지 확인,
				Member member = memberRepository.findByGithubId(contributor.getId().toString())
					.orElse(null);
				// 우리 멤버라면
				if (member != null) {
					// repository contributor 관계 존재하는지 확인
					// 존재한다면 pass
					// 존재하지 않으면 insert
					RepositoryContributor repositoryContributor = repoContributorRepository.findByMemberAndRepository(
						member, repo).orElse(null);

					if (repositoryContributor == null) {
						RepositoryContributor con = RepositoryContributor
							.builder()
							.repository(repo)
							.member(member)
							.build();

						repoContributorRepository.save(con);
					}
				} else {
					// 우리 멤버가 아니라면 github contributor 에 있는지 보고
					GithubContributor githubContributor = githubContributorRepository.findByAuthorId(
						String.valueOf(contributor.getId())).orElse(null);

					// 있다면
					if (githubContributor != null) {
						// 관계 연결돼 있는지 보고
						RepositoryGithubContributor repositoryGithubContributor = repositoryGithubContributorRepository.findByGithubContributorAndRepository(
							githubContributor, repo).orElse(null);
						// 관계 없다면
						if (repositoryGithubContributor == null) {
							// 관계 insert
							repositoryGithubContributorRepository.save(
								RepositoryGithubContributor.builder()
									.githubContributor(githubContributor)
									.repository(repo)
									.build()
							);
						}

						// 없다면
					} else {
						GithubContributor con = GithubContributor.builder()
							.authorId(String.valueOf(contributor.getId()))
							.nickname(contributor.getLogin())
							.githubUrl(contributor.getHtmlUrl())
							.avatarUrl(contributor.getAvatarUrl())
							.build();

						// github contributor insert
						githubContributorRepository.save(con);

						// 관계 insert
						repositoryGithubContributorRepository.save(
							RepositoryGithubContributor.builder()
								.githubContributor(con)
								.repository(repo)
								.build()
						);
					}

				}

			}
			if (contributorDtos.length < 100) {
				break;
			}

			page += 1;
		}

	}

	public void getGithubContribute(Member member) {
		// 먼저 githubContributor 에 있는 회원인가 보고
		GithubContributor githubContributor = githubContributorRepository.findByAuthorId(member.getGithubId())
			.orElse(null);

		// 있으면
		if (githubContributor != null) {
			// repository - github contributor 삭제하고
			// githubContributor에 있다는 건 무조건 1 이상의 관계를 가지고 있다는 뜻
			List<RepositoryGithubContributor> repositoryGithubContributors = repositoryGithubContributorRepository.findByGithubContributor(
				githubContributor);

			for (RepositoryGithubContributor repositoryGithubContributor : repositoryGithubContributors) {
				// repository contributor 에 연결
				repoContributorRepository.save(
					RepositoryContributor.builder()
						.repository(repositoryGithubContributor.getRepository())
						.member(member)
						.build()
				);

				repositoryGithubContributor.setGithubContributor(null);
				repositoryGithubContributor.setRepository(null);

				repositoryGithubContributorRepository.deleteById(repositoryGithubContributor.getId());
			}

			// githubContributor 삭제하고
			githubContributorRepository.deleteById(githubContributor.getId());

		}

	}

	public ContributorDto[] getContributors(String githubToken, String repositoryFullName, String page) {
		ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
			.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // 10MB
			.build();

		WebClient webClient = WebClient.builder()
			.exchangeStrategies(exchangeStrategies)
			.build();

		return webClient
			.get()
			.uri(GitHub.getPublicRepositoryContributorsUrl(repositoryFullName, page))
			.header("Authorization", "token " + githubToken)
			.retrieve().bodyToMono(ContributorDto[].class).block();
	}

	public Map<String, Long> getRepositoryLanguages(String githubToken, String repositoryFullName, String page) {
		ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
			.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // 10MB
			.build();

		WebClient webClient = WebClient.builder()
			.exchangeStrategies(exchangeStrategies)
			.build();

		return webClient
			.get()
			.uri(
				GitHub.getPublicRepositoryLanguageUrl(repositoryFullName, page)
			)
			.header("Authorization", "token " + githubToken)
			.retrieve().bodyToMono(new ParameterizedTypeReference<Map<String, Long>>() {
			}).block();
	}
}
