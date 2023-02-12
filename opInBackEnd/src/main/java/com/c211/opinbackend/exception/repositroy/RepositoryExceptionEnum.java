package com.c211.opinbackend.exception.repositroy;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RepositoryExceptionEnum {
	REPOSITORY_NULL_EXCEPTION(HttpStatus.BAD_REQUEST, "M0002", "래포지토리 가 null 입니다."),
	REPOSITORY_EXIST_EXCEPTION(HttpStatus.BAD_REQUEST, "M0008", "존재하지 않는 래포입니다."),
	REPOSITORY_SAVE_EXCEPTION(HttpStatus.BAD_REQUEST, "M0008", "래포지토리 저장을 실패했습니다"),

	REPOSITORY_POST_SAVE_EXCEPTION(HttpStatus.BAD_REQUEST, "M0009", "래포지토리 포스트 등록 실패"),
	REPOSITORY_POST_EXIST_EXCEPTION(HttpStatus.BAD_REQUEST, "M0010", "래포지토리 포스트 가 존재하지 않습니다."),
	REPOSITORY_POST_COMMENT_SAVE_EXCEPTION(HttpStatus.BAD_REQUEST, "M0011", "포스트 타입 댓글 저장에 실패했습니다."),
	REPOSITORY_QNA_CONTENT_EMPTY_EXCEPTION(HttpStatus.BAD_REQUEST, "M0012", "질문이 빈칸일수 없습니다."),
	REPOSITORY_QNA_COMMENT_EMPTY_EXCEPTION(HttpStatus.BAD_REQUEST, "M0012", "댓글이 빈칸일수 없습니다."),
	REPOSITORY_POST_TITLE_EMPTY_EXCEPTION(HttpStatus.BAD_REQUEST, "M0013", "포스트 제목은 빈칸일수 없습니다.."),
	REPOSITORY_POST_LIKE_DELETE_EXCEPTION(HttpStatus.BAD_REQUEST, "M0014", "좋아요를 하지 않았는데 취소를 시도합니다."),
	REPOSITORY_POST_LIKE_SAVE_EXCEPTION(HttpStatus.BAD_REQUEST, "M0015", "이미 좋아요 를 하고 있는 포스트입니다."),
	REPOSITORY_QNA_EXIST_EXCEPTION(HttpStatus.BAD_REQUEST, "M0016", "QNA 가 존재하지 않습니다.");

	private final HttpStatus httpStatus;
	private final String httpCode;
	private final String errorMessage;
}
