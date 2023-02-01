package com.c211.opinbackend.exception.repositroy;

import lombok.Getter;

@Getter
public class RepositoryRuntimeException extends RuntimeException {
	private RepositoryExceptionEnum errorEnum;

	public RepositoryRuntimeException(RepositoryExceptionEnum errorEnum) {
		super(errorEnum.getErrorMessage());
		this.errorEnum = errorEnum;
	}
}
