package com.c211.opinbackend.auth.model;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

	private Long id;
	private String email;

	private String password;
	private String nickname;

	private String avatarUrl;

	private boolean githubSyncFl;

	private String githubToken;
	private String githubId;

	private Role role;

	public Member toMember() {
		return Member.builder()
			.githubId(githubId)
			.email(email)
			.nickname(nickname)
			.avatarUrl(avatarUrl)
			.role(Role.ROLE_USER)
			.githubToken(githubToken)
			.githubSyncFl(githubSyncFl)
			.password(password)
			.id(id)
			.build();
	}
}
