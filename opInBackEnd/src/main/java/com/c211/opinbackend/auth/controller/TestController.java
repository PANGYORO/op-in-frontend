package com.c211.opinbackend.auth.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.auth.model.request.MemberJoinRequest;
import com.c211.opinbackend.batch.dto.RepoTechLanguageDto;
import com.c211.opinbackend.batch.dto.github.RepositoryDto;
import com.c211.opinbackend.constant.GitHub;
import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.Role;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/king")
public class TestController {

	private final MemberRepository memberRepository;
	private final RepoRepository repoRepository;

	@PostMapping("/hi")
	public void signUp() throws Exception {
		List<Member> members = memberRepository.findAllByGithubIdIsNotNull();

		for (Member member : members) {
			RepositoryDto[] memberRepository = getMemberRepository(member.getGithubUserName());

			for (RepositoryDto repo : memberRepository) {
				log.info(repo.getFullName());
			}
		}

		List<Repository> repos = repoRepository.findAll();

		for (Repository repo : repos) {
			Map<String, Long> languages = getRepositoryLanguages(repo.getFullName());
			for (String lan : languages.keySet()) {
				log.info(lan);
			}
		}

	}
	public RepositoryDto[] getMemberRepository(String githubUserName) {
		return WebClient.create()
			.get()
			.uri(GitHub.getUserRepoUrl(githubUserName))
			.header("Authorization", "token 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")
			.retrieve().bodyToMono(RepositoryDto[].class).block();
	}

	public static Map<String, Long> getRepositoryLanguages(String repositoryFullName) {
		return WebClient.create()
			.get()
			.uri(
				GitHub.getPublicRepositoryLanguageUrl(repositoryFullName)
			)
			.header("Authorization", "token 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")
			.retrieve().bodyToMono(new ParameterizedTypeReference<Map<String, Long>>() {
			}).block();
	}
}
