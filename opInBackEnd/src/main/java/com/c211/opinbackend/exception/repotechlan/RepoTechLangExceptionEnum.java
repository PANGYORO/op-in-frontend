package com.c211.opinbackend.exception.repotechlan;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RepoTechLangExceptionEnum {

	//user 관련

	REPO_TECH_LANG_EXCEPTION(HttpStatus.BAD_REQUEST, "M0001", "래포지토에서 사용하는 언어가 없습니다.");

	private final HttpStatus httpStatus;
	private final String httpCode;
	private final String errorMessage;
}
