package com.c211.opinbackend.exception.repositroy;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RepositoryExceptionEntity {
	private HttpStatus httpCode;
	private String errorMessage;

}
