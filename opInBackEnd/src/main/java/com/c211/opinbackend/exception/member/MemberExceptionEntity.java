package com.c211.opinbackend.exception.member;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MemberExceptionEntity {
	private HttpStatus httpCode;
	private String errorMessage;

}
