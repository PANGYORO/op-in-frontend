package dev.opin.opinbackend.exception.api;

import lombok.Getter;

@Getter
public class ApiRuntimeException extends RuntimeException {
	private ApiExceptionEnum errorEnum;

	public ApiRuntimeException(ApiExceptionEnum errorEnum) {
		super(errorEnum.getErrorMessage());
		this.errorEnum = errorEnum;
	}
}
