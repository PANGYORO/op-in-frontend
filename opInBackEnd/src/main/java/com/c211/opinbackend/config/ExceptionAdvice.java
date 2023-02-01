package com.c211.opinbackend.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.c211.opinbackend.exceoption.member.MemberExceptionEntity;
import com.c211.opinbackend.exceoption.member.MemberRuntimeException;

/**
 * The type Exception advice.
 */
@RestControllerAdvice
public class ExceptionAdvice {

	/**
	 * 맴버에 관련된 런타임 에러입니다
	 * @param runError
	 * @return
	 */
	@ExceptionHandler({MemberRuntimeException.class})
	private ResponseEntity<MemberExceptionEntity> apiExceptionHandler(
		final MemberRuntimeException runError) {
		return new ResponseEntity<>(
			new MemberExceptionEntity(runError.getErrorEnum().getHttpStatus(), runError.getMessage()),
			runError.getErrorEnum().getHttpStatus());
	}
}
