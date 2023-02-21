package dev.opin.opinbackend.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import dev.opin.opinbackend.exception.ExceptionResponseEntity;
import dev.opin.opinbackend.exception.api.ApiExceptionEnum;
import dev.opin.opinbackend.exception.api.ApiRuntimeException;
import dev.opin.opinbackend.exception.auth.AuthRuntimeException;
import dev.opin.opinbackend.exception.event.EventExceptionRuntimeException;
import dev.opin.opinbackend.exception.member.MemberRuntimeException;
import dev.opin.opinbackend.exception.repositroy.RepositoryRuntimeException;

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
	@ExceptionHandler({MemberRuntimeException.class})
	private ResponseEntity<ExceptionResponseEntity> memberExceptionHandler(
		final MemberRuntimeException runError) {
		return new ResponseEntity<>(
			new ExceptionResponseEntity(
				runError.getErrorEnum().getHttpStatus().value(),
				runError.getErrorEnum().getHttpCode(),
				runError.getMessage()
			),
			runError.getErrorEnum().getHttpStatus());
	}

	/**
	 * @param exception
	 * 존재하지 않는 API에 대한 기본 메세지 처리입니다.
	 */
	@ExceptionHandler({NoHandlerFoundException.class})
	private ResponseEntity<ExceptionResponseEntity> apiExceptionHandler(
		final Exception exception) {
		ApiRuntimeException runError = new ApiRuntimeException(ApiExceptionEnum.API_NOT_EXIST_EXCEPTION);
		return new ResponseEntity<>(
			new ExceptionResponseEntity(
				runError.getErrorEnum().getHttpStatus().value(),
				runError.getErrorEnum().getHttpCode(),
				runError.getMessage()
			),
			runError.getErrorEnum().getHttpStatus());
	}

	/**
	 * 권한과 인증관련된애러 처리입니다.
	 * @param runError
	 * @return
	 */
	@ExceptionHandler({AuthRuntimeException.class})
	private ResponseEntity<ExceptionResponseEntity> authExceptionHandler(
		final AuthRuntimeException runError) {
		return new ResponseEntity<>(
			new ExceptionResponseEntity(
				runError.getErrorEnum().getHttpStatus().value(),
				runError.getErrorEnum().getHttpCode(),
				runError.getMessage()
			),
			runError.getErrorEnum().getHttpStatus());
	}

	@ExceptionHandler({RepositoryRuntimeException.class})
	private ResponseEntity<ExceptionResponseEntity> repoExceptionHandler(
		final RepositoryRuntimeException runError) {
		return new ResponseEntity<>(
			new ExceptionResponseEntity(
				runError.getErrorEnum().getHttpStatus().value(),
				runError.getErrorEnum().getHttpCode(),
				runError.getMessage()
			),
			runError.getErrorEnum().getHttpStatus());
	}

	@ExceptionHandler({EventExceptionRuntimeException.class})
	private ResponseEntity<ExceptionResponseEntity> eventExceptionHandler(
		final EventExceptionRuntimeException runError) {
		return new ResponseEntity<>(
			new ExceptionResponseEntity(
				runError.getErrorEnum().getHttpStatus().value(),
				runError.getErrorEnum().getHttpCode(),
				runError.getMessage()
			),
			runError.getErrorEnum().getHttpStatus());
	}
}

