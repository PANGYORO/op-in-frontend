package dev.opin.opinbackend.auth.model;

import dev.opin.opinbackend.persistence.entity.Member;
import dev.opin.opinbackend.persistence.entity.Role;

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
	private String githubUserName;

	private Role role;

	public Member toMember() {
		return Member.builder()
			.id(id)
			.password(password)
			.email(email)
			.nickname(nickname)
			.avatarUrl(avatarUrl)
			.githubId(githubId)
			.githubToken(githubToken)
			.githubSyncFl(githubSyncFl)
			.githubUserName(githubUserName)
			.role(role)
			.build();
	}
}
