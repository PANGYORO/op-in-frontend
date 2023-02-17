package com.c211.opinbackend.search.dto.response;

import java.time.LocalDateTime;

import com.c211.opinbackend.persistence.entity.CommentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDto {
	private MemberDto member;
	private Long repositoryId;
	private Long repositoryPostId;

	private Long repositoryQnaId;
	private String comment;
	private CommentType commentType;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime createDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime updateDate;
}
