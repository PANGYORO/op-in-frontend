package com.c211.opinbackend.exceoption.auth;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthExceptionEntity {
	private HttpStatus httpCode;
	private String errorMessage;

}
