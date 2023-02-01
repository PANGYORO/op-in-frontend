package com.c211.opinbackend.exception.auth;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthExceptionEnum {

	//권한 관련
	AUTH_AUTHENTICATION_EXCEPTION(HttpStatus.FORBIDDEN, "M0001", "권한이 잘못됬습니다 ."),
	AUTH_AUTHORIZATION_EXCEPTION(HttpStatus.FORBIDDEN, "M0001", "인증이 잘못됬습니다.");

	private final HttpStatus httpStatus;
	private final String httpCode;
	private final String errorMessage;
}
