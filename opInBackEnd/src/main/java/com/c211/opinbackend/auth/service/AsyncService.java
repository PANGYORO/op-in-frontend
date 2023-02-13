package com.c211.opinbackend.auth.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.c211.opinbackend.auth.mapper.RepoMapper;
import com.c211.opinbackend.auth.model.RepositoryDto;
import com.c211.opinbackend.constant.GitHub;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.repository.RepoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AsyncService {

	RepoRepository repoRepository;

	public AsyncService(RepoRepository repoRepository) {
		this.repoRepository =repoRepository;
	}
	@Async
	public CompletableFuture<String> process(final Member member) {
		try {
			RepositoryDto[] dtos = getMemberRepository(member.getGithubToken(), member.getGithubUserName());
			for (RepositoryDto repo : dtos) {
				try {
					Repository repository = RepoMapper.toRepository(repo, member);
					repoRepository.save(repository);
					log.info("[SUCCESS] github 유저: {} || repository update: {}", member.getGithubId(), repository.getId());
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
			.uri(GitHub.getUserRepoUrl(githubUserName))
			.header("Authorization", "token "+githubToken)
			.retrieve().bodyToMono(RepositoryDto[].class).block();
	}
}
