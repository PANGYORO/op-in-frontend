package com.c211.opinbackend.auth.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.c211.opinbackend.auth.mapper.RepoMapper;
import com.c211.opinbackend.auth.model.RepositoryDto;
import com.c211.opinbackend.constant.GitHub;
import com.c211.opinbackend.persistence.entity.BatchToken;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.MemberTechLanguage;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryTechLanguage;
import com.c211.opinbackend.persistence.entity.TechLanguage;
import com.c211.opinbackend.persistence.repository.MemberTechLanguageRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.persistence.repository.RepoTechLanguageRepository;
import com.c211.opinbackend.persistence.repository.TechLanguageRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AsyncService {

	RepoRepository repoRepository;
	TechLanguageRepository techLanguageRepository;
	RepoTechLanguageRepository repoTechLanguageRepository;
	MemberTechLanguageRepository memberTechLanguageRepository;

	public AsyncService(RepoRepository repoRepository,
		TechLanguageRepository techLanguageRepository,
		RepoTechLanguageRepository repoTechLanguageRepository,
		MemberTechLanguageRepository memberTechLanguageRepository) {
		this.repoRepository = repoRepository;
		this.techLanguageRepository = techLanguageRepository;
		this.repoTechLanguageRepository = repoTechLanguageRepository;
		this.memberTechLanguageRepository = memberTechLanguageRepository;
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
					getMemberContribute();

				} catch (Exception e) {
					log.error("[FAILED] github 유저: {} || repository update: {}", member.getGithubId(), repo.getId());
				}
			}
			return CompletableFuture.completedFuture("SUCCESS");
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		}
		return CompletableFuture.completedFuture("FAILED");
	}

	public static RepositoryDto[] getMemberRepository(String githubToken, String githubUserName) {
		return WebClient.create()
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

	public void getMemberContribute(){
		// 먼저 githubContributor 에 있는 회원인가 보고
		// 있으면 githubContributor 삭제하고
		// repository - github contributor 삭제하고
		// repository contributor 에 연결
		// 없으면 pass
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
