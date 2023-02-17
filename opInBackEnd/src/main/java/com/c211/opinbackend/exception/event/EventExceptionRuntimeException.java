package com.c211.opinbackend.exception.event;

import lombok.Getter;

@Getter
public class EventExceptionRuntimeException extends RuntimeException {
	private EventExceptionEnum errorEnum;

	public EventExceptionRuntimeException(EventExceptionEnum errorEnum) {
		super(errorEnum.getErrorMessage());
		this.errorEnum = errorEnum;
	}
}
