package com.c211.opinbackend.exception.repositroy;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RepositoryExceptionEnum {
	REPOSITORY_EXIST_EXCEPTION(HttpStatus.BAD_REQUEST, "M0010", "존재하지 않는 래포입니다.");

	private final HttpStatus httpStatus;
	private final String httpCode;
	private final String errorMessage;
}
