package com.c211.opinbackend.auth.model.response;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.model.MemberDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthUserInfoResponse {
	private String login;
	private String name;
	private String id;

	@JsonProperty("avatar_url")
	private String avatar;

	private String email;
}
