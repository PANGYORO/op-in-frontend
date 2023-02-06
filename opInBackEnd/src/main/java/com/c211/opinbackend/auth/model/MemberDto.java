package com.c211.opinbackend.auth.model;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
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
			.id(id)
			.password(password)
			.githubId(githubId)
			.email(email)
			.nickname(nickname)
			.avatarUrl(avatarUrl)
			.githubToken(githubToken)
			.githubSyncFl(githubSyncFl)
			.role(role)
			.build();
	}
}
