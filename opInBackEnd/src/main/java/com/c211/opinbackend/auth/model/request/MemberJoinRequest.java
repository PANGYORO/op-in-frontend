package com.c211.opinbackend.auth.model.request;

import lombok.Getter;

@Getter
public class MemberJoinRequest {

	private String email;
	private String password;
	private String nickname;
}
