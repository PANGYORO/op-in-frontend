package com.c211.opinbackend.exception.auth;

import lombok.Getter;

@Getter
public class AuthRuntimeException extends RuntimeException {
	private AuthExceptionEnum errorEnum;

	public AuthRuntimeException(AuthExceptionEnum errorEnum) {
		super(errorEnum.getErrorMessage());
		this.errorEnum = errorEnum;
	}
}
