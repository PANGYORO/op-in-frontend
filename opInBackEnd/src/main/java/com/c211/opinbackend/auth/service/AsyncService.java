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
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryTechLanguage;
import com.c211.opinbackend.persistence.entity.TechLanguage;
import com.c211.opinbackend.persistence.repository.RepoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AsyncService {

	RepoRepository repoRepository;

	public AsyncService(RepoRepository repoRepository) {
		this.repoRepository = repoRepository;
	}

	@Async
	public CompletableFuture<String> process(final Member member) {
		try {
			RepositoryDto[] dtos = getMemberRepository(member.getGithubToken(), member.getGithubUserName());
			for (RepositoryDto repo : dtos) {
				// dto 에 따라 tech language 가져오기

				try {
					Repository repository = RepoMapper.toRepository(repo, member);
					repoRepository.save(repository);
					log.info("[SUCCESS] github 유저: {} || repository update: {}",
						member.getGithubId(), repository.getId());
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

	public static void getMemberTechLanguage(String githubToken, RepositoryDto repo) {
		/**
			int page = 1;
			while (true) {
					Map<String, Long> languages = getRepositoryLanguages(githubToken, repo.getFullName(),
						String.valueOf(page));

					for (String lan : languages.keySet()) {
						try {
							TechLanguage techLanguage = techLanguageRepository.findByTitle(lan)
								.orElse(null);

							if (techLanguage == null) {
								techLanguage = techLanguageRepository.save(
									TechLanguage.builder().title(lan).build());
							}

							RepositoryTechLanguage repoTechRelation = repoTechLanguageRepository.findByRepositoryAndTechLanguage(
								repo, techLanguage).orElse(null);

							if (repoTechRelation == null) {
								repoTechLanguageRepository.save(RepositoryTechLanguage.builder()
									.techLanguage(techLanguage)
									.repository(repo)
									.build());
							}
						} catch (Exception e) {
							log.info(e.toString());
						}
					}

					if (languages.size() < 100) {
						break;
					}

					page += 1;

		}
		return RepeatStatus.FINISHED;
		 */
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
