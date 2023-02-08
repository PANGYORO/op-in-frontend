package com.c211.opinbackend.batch.dto.mapper;

import com.c211.opinbackend.batch.dto.opin.GitHubMemberDto;
import com.c211.opinbackend.persistence.entity.Member;

public class MemberMapper {
	public static GitHubMemberDto toGitHubMemberDto(Member member) {
		return GitHubMemberDto.builder()
			.id(member.getId())
			.githubToken(member.getGithubToken())
			.githubId(member.getGithubId())
			.githubUserName(member.getGithubUserName())
			.build();
	}
}
