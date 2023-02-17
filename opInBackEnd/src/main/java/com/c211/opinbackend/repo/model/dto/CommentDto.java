package com.c211.opinbackend.repo.model.dto;

import com.c211.opinbackend.persistence.entity.CommentType;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentDto {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long id;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String memberNickname;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long repositoryId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long repositoryQnA;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long repositoryPostId;
	private String content;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private CommentType commentType;
}
