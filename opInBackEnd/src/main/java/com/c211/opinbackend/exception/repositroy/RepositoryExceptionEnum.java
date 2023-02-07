package com.c211.opinbackend.exception.repositroy;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RepositoryExceptionEnum {
	REPOSITORY_NULL_EXCEPTION(HttpStatus.BAD_REQUEST, "M0002", "래포지토리 가 null 입니다."),
	REPOSITORY_EXIST_EXCEPTION(HttpStatus.BAD_REQUEST, "M0010", "존재하지 않는 래포입니다."),

	REPOSITORY_POST_SAVE_EXCEPTION(HttpStatus.BAD_REQUEST, "M0011", "래포지토리 포스트 등록 실패");

	private final HttpStatus httpStatus;
	private final String httpCode;
	private final String errorMessage;
}
