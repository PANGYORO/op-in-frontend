package com.c211.opinbackend.exception.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthExceptionEnum {

	AUTH_AUTHORIZATION_EXCEPTION(HttpStatus.UNAUTHORIZED, "A0000", "인증되지 않았습니다."), //권한 관련
	AUTH_AUTHENTICATION_EXCEPTION(HttpStatus.FORBIDDEN, "A0001", "권한이 없습니다."),

	AUTH_JWT_EXPIRED_EXCEPTION(HttpStatus.UNAUTHORIZED, "A0002", "JWT 토큰이 만료됐습니다."),

	AUTH_JWT_SUPPORT_EXCEPTION(HttpStatus.UNAUTHORIZED, "A0003", "지원하지 않는 JWT 토큰 입니다."),

	AUTH_SECURITY_AUTHENTICATION_EXCEPTION(HttpStatus.UNAUTHORIZED, "A0005", "Security UsernamePasswordAuthenticationToken 에러입니다."),

	AUTH_JWT_SIGNATURE_EXCEPTION(HttpStatus.UNAUTHORIZED, "A0004", "잘못된 JWT 서명입니다.");

	private final HttpStatus httpStatus;
	private final String httpCode;
	private final String errorMessage;

	public static Map<String, Object> convertMap(AuthExceptionEnum ex) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", ex.getHttpStatus().value());
		map.put("code", ex.getHttpCode());
		map.put("message", ex.getErrorMessage());

		return map;
	}
}
