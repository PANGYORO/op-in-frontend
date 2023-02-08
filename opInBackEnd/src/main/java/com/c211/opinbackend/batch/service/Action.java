package com.c211.opinbackend.batch.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.batch.dto.GitHubMemberDto;
import com.c211.opinbackend.batch.service.mapper.GithubSyncMemberMapper;
import com.c211.opinbackend.constant.GitHub;
import com.c211.opinbackend.member.service.MemberService;
import com.c211.opinbackend.persistence.entity.Role;
import com.c211.opinbackend.util.RandomString;

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
				GithubSyncMemberMapper.toGitHubMemberDto(member)
			)
			.collect(Collectors.toList());
	}

	/**
	 * webClient로 gitHub 유저 정보를 가져옵니다.
	 * @param githubToken
	 * @return
	 */
	private Map<String, Object> getUserAttributes(String githubToken) {
		return WebClient.create()
			.get()
			.uri(GitHub.USER_INFO_URL)
			.headers(header -> header.setBearerAuth(githubToken))
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
			})

			.block();
	}

	private void getMemberRepository(GitHubMemberDto memberDto) {
		// return WebClient.create()
		// 	.get()
	}

	private MemberDto getUserProfile(String githubToken) {
		Map<String, Object> userAttributes = getUserAttributes(githubToken);
		return MemberDto.builder()
			.githubId(String.valueOf(userAttributes.get("id")))
			.githubToken(githubToken)
			.githubSyncFl(true)
			.password(new BCryptPasswordEncoder().encode(""))
			.email(userAttributes.get("id") + "@github.io")
			.nickname(userAttributes.get("login") + "." + RandomString.generateNumber())
			.avatarUrl((String)userAttributes.get("avatar_url"))
			.role(Role.ROLE_USER)
			.build();
	}
}
