package com.c211.opinbackend.auth.model.request;

import lombok.Getter;

@Getter
public class MemberLoginRequest {
	private String email;
	private String password;
}
