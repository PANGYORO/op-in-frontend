package com.c211.opinbackend.exception.member;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberExceptionEnum {

	//user 관련
	MEMBER_ACCESS_EXCEPTION(HttpStatus.FORBIDDEN, "M0001", "접근 권한이 없습니다."),
	MEMBER_EXIST_NICKNAME_EXCEPTION(HttpStatus.BAD_REQUEST, "M0002", "이미 존재하는 닉네임 입니다."),
	MEMBER_EXIST_EMAIL_EXCEPTION(HttpStatus.BAD_REQUEST, "M0002", "이미 존재하는 이메일 입니다."),
	MEMBER_NOT_EXIST_EXCEPTION(HttpStatus.BAD_REQUEST, "M0003", "존재하지않는 유저입니다."),
	MEMBER_TYPE_EXCEPTION(HttpStatus.BAD_REQUEST, "M0010", "존재하지 않는 멤버 타입입니다."),
	MEMBER_PASSWORD_EXCEPTION(HttpStatus.BAD_REQUEST, "M0011", "비밀번호를 확인해주세요."),
	MEMBER_PASSWORD_TYPE_EXCEPTION(HttpStatus.BAD_REQUEST, "M0012", "잘못된 비밀번호 양식입니다.");

	private final HttpStatus httpStatus;
	private final String httpCode;
	private final String errorMessage;
}
