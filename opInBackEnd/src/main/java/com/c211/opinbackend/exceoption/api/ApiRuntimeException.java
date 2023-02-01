package com.c211.opinbackend.exceoption.api;

import lombok.Getter;

@Getter
public class ApiRuntimeException extends RuntimeException {
	private ApiExceptionEnum errorEnum;

	public ApiRuntimeException(ApiExceptionEnum errorEnum) {
		super(errorEnum.getErrorMessage());
		this.errorEnum = errorEnum;
	}
}
