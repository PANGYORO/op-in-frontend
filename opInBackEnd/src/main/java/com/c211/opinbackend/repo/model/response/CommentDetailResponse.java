package com.c211.opinbackend.repo.model.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CommentDetailResponse {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long qnaId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long id;

	private String memberName;
	private String memberAvatarUrl;

	private String commentContent;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime createDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime updateDate;
}
