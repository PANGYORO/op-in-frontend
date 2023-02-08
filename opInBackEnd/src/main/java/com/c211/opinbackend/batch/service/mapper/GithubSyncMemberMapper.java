package com.c211.opinbackend.batch.service.mapper;

import com.c211.opinbackend.batch.dto.GitHubMemberDto;
import com.c211.opinbackend.persistence.entity.Member;

public class GithubSyncMemberMapper {
	public static GitHubMemberDto toGitHubMemberDto(Member member) {
		return GitHubMemberDto.builder()
			.id(member.getId())
			.githubToken(member.getGithubToken())
			.githubId(member.getGithubId())
			.build();
	}
}
