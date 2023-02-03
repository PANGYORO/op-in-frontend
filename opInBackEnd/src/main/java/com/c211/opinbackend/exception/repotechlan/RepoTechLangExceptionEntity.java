package com.c211.opinbackend.exception.repotechlan;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RepoTechLangExceptionEntity {
	private HttpStatus httpCode;
	private String errorMessage;

}
