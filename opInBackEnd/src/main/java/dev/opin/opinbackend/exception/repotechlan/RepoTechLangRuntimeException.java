package dev.opin.opinbackend.exception.repotechlan;

import lombok.Getter;

@Getter
public class RepoTechLangRuntimeException extends java.lang.RuntimeException {
	private RepoTechLangExceptionEnum errorEnum;

	public RepoTechLangRuntimeException(RepoTechLangExceptionEnum errorEnum) {
		super(errorEnum.getErrorMessage());
		this.errorEnum = errorEnum;
	}
}
