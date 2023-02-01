package com.c211.opinbackend.exceoption.member;

import lombok.Getter;

@Getter
public class MemberRuntimeException extends RuntimeException {
	private MemberExceptionEnum errorEnum;

	public MemberRuntimeException(MemberExceptionEnum errorEnum) {
		super(errorEnum.getErrorMessage());
		this.errorEnum = errorEnum;
	}
}
