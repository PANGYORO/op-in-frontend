package com.c211.opinbackend.auth.model;

import com.c211.opinbackend.auth.entity.GithubSync;

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

	private String avataUrl;

	private boolean githubSyncFl;

	private GithubSync githubSync;
}
