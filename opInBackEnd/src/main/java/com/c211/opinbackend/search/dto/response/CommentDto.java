package com.c211.opinbackend.search.dto.response;

import com.c211.opinbackend.persistence.entity.CommentType;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CommentDto {
	private String memberEmail;
	private Long repositoryId;
	private Long repositoryPostId;

	private Long repositoryQnaId;
	private String comment;
	private CommentType commentType;
}
