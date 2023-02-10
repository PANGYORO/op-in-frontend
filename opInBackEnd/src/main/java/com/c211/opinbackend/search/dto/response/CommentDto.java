package com.c211.opinbackend.search.dto.response;

import com.c211.opinbackend.persistence.entity.CommentType;
import com.fasterxml.jackson.annotation.JsonInclude;

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
}
