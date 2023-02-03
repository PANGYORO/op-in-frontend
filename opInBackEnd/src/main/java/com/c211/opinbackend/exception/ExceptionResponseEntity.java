package com.c211.opinbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponseEntity {
	private int status;
	private String code;
	private String message;

}
