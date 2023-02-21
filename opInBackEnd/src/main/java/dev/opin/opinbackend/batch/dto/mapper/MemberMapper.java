package dev.opin.opinbackend.batch.dto.mapper;

import org.springframework.stereotype.Component;

import dev.opin.opinbackend.batch.dto.opin.GitHubMemberDto;
import dev.opin.opinbackend.persistence.entity.Member;

@Component
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
