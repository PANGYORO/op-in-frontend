package dev.opin.opinbackend.exception.event;

import lombok.Getter;

@Getter
public class EventExceptionRuntimeException extends RuntimeException {
	private EventExceptionEnum errorEnum;

	public EventExceptionRuntimeException(EventExceptionEnum errorEnum) {
		super(errorEnum.getErrorMessage());
		this.errorEnum = errorEnum;
	}
}
