package dev.opin.opinbackend.exception.api;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApiExceptionEnum {

	RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001", "내부 문제로 다음번에 다시 시도해주세요."),
	ACCESS_DENIED_EXCEPTION(
		HttpStatus.UNAUTHORIZED, "E0002", "권한이 없습니다."),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0003",
		"내부 문제로 다음번에 다시 시도해주세요."),
	API_NOT_EXIST_EXCEPTION(HttpStatus.NOT_FOUND, "E0004",
		"존재하지 않는 API 입니다."),
	API_METHOD_NOT_ALLOWED_EXCEPTION(HttpStatus.METHOD_NOT_ALLOWED, "E0005",
		"지원하지 않는 Method입니다."),
	API_PARAMETER_EXCEPTION(HttpStatus.BAD_REQUEST, "E0006",
		"파라미터 타입과 값을 확인하세요."),
	API_NEW_FUNCTION_WAIT_REQUEST(HttpStatus.BAD_REQUEST, "E0000", "준비중..."),

	API_OAUTH_CENTER_CALL_EXCEPTION(HttpStatus.BAD_REQUEST, "E0007", "github 로그인 중 오류가 발생했습니다. 고객센터에 문의주세요."),

	API_CENTER_CALL_EXCEPTION(HttpStatus.BAD_REQUEST, "E0007", "메일 발송 실패."),

	API_WORK_FAILED_EXCEPTION(HttpStatus.BAD_REQUEST, "E0008", "API 실행 중 오류 발생.");

	private final HttpStatus httpStatus;
	private final String httpCode;
	private final String errorMessage;
}
