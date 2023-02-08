package com.c211.opinbackend.batch.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.c211.opinbackend.batch.dto.github.RepositoryDto;
import com.c211.opinbackend.batch.dto.github.UserDto;
import com.c211.opinbackend.batch.dto.opin.GitHubMemberDto;
import com.c211.opinbackend.batch.service.mapper.MemberMapper;
import com.c211.opinbackend.constant.GitHub;
import com.c211.opinbackend.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class Action {

	private final MemberService memberService;

	private List<GitHubMemberDto> getGitHubSyncMembers() {
		return memberService.getGitHubSyncMembers()
			.stream()
			.map(member ->
				MemberMapper.toGitHubMemberDto(member)
			)
			.collect(Collectors.toList());
	}

	public static UserDto getMemberInfo(String githubUserName) {
		return WebClient.create()
			.get()
			.uri(GitHub.getUserInfoUrl(githubUserName))
			.retrieve()
			.bodyToMono(UserDto.class).block();
	}

	public static RepositoryDto[] getMemberRepository(String githubToken, String githubUserName) {
		return WebClient.create()
			.get()
			.uri(GitHub.getUserRepoUrl(githubUserName))
			.headers(header -> {
				if (githubToken != null && !githubToken.isEmpty()) {
					header.setBearerAuth(githubToken);
				}
			}).retrieve().bodyToMono(RepositoryDto[].class).block();
	}
}
