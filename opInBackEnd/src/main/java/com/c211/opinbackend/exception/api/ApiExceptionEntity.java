package com.c211.opinbackend.exception.api;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiExceptionEntity {
	private HttpStatus httpCode;
	private String errorMessage;

}
