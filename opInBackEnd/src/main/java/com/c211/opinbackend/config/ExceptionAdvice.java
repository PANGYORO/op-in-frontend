package com.c211.opinbackend.config;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.c211.opinbackend.exception.api.ApiExceptionEnum;
import com.c211.opinbackend.exception.api.ApiRuntimeException;
import com.c211.opinbackend.exception.auth.AuthRuntimeException;
import com.c211.opinbackend.exception.repositroy.RepositoryExceptionEntity;
import com.c211.opinbackend.exception.repositroy.RepositoryRuntimeException;

/**
 * The type Exception advice.
 */
@RestControllerAdvice
public class ExceptionAdvice {

	/**
	 * throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION)
	 * 맴버에 관련된 런타임 에러입니다
	 * @param runError
	 * @return
	 */
	@ExceptionHandler({RepositoryRuntimeException.class})
	private ResponseEntity<RepositoryExceptionEntity> memberExceptionHandler(
		final RepositoryRuntimeException runError) {
		return new ResponseEntity<>(
			new RepositoryExceptionEntity(runError.getErrorEnum().getHttpStatus(), runError.getMessage()),
			runError.getErrorEnum().getHttpStatus());
	}

	/**
	 * @param exception
	 * 존재하지 않는 API에 대한 기본 메세지 처리입니다.
	 */
	@ExceptionHandler({NoHandlerFoundException.class})
	private ResponseEntity<RepositoryExceptionEntity> apiExceptionHandler(
		final Exception exception) {
		ApiRuntimeException runError = new ApiRuntimeException(ApiExceptionEnum.API_NOT_EXIST_EXCEPTION);
		return new ResponseEntity<>(
			new RepositoryExceptionEntity(runError.getErrorEnum().getHttpStatus(), runError.getMessage()),
			runError.getErrorEnum().getHttpStatus());
	}

	/**
	 * 권한과 인증관련된애러 처리입니다.
	 * @param runError
	 * @return
	 */
	@ExceptionHandler({AuthenticationException.class})
	private ResponseEntity<RepositoryExceptionEntity> authExceptionHandler(
		final AuthRuntimeException runError) {
		return new ResponseEntity<>(
			new RepositoryExceptionEntity(runError.getErrorEnum().getHttpStatus(), runError.getMessage()),
			runError.getErrorEnum().getHttpStatus());
	}

}
