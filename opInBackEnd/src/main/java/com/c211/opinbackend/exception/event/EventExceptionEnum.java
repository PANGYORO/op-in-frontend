package com.c211.opinbackend.exception.event;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventExceptionEnum {
	EVENT_TITLE_NULL_EXCEPTION(HttpStatus.BAD_REQUEST, "M0002", "이벤트의 제목을 넣어주세요"),
	EVENT_CONTENT_NULL_EXCEPTION(HttpStatus.BAD_REQUEST, "M0003", "이벤트의 내용을 넣어주세요"),
	EVENT_SAVE_FAIL_EXCEPTION(HttpStatus.BAD_REQUEST, "M0004", "이벤트 저장을 실패 했습니다."),
	EVENT_OPEN_DATE_NULL_EXCEPTION(HttpStatus.BAD_REQUEST, "M0005", "이벤트의 오픈일을 넣어주세요"),
	EVENT_NOT_EXIST_EXCEPTION(HttpStatus.BAD_REQUEST, "M0006", "이벤트의 가 디비에 없습니다."),
	EVENT_OPEN_DATE_WRONG_EXCEPTION(HttpStatus.BAD_REQUEST, "M0007", "오픈일 과 끝나는 날이 잘못됬습니다.");
	private final HttpStatus httpStatus;
	private final String httpCode;
	private final String errorMessage;
}
