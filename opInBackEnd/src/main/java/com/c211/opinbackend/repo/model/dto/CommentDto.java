package com.c211.opinbackend.repo.model.dto;

import com.c211.opinbackend.persistence.entity.CommentType;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentDto {
	private String memberEmail;
	private Long repositoryId;
	private Long repositoryPostId;
	private String comment;
	private CommentType commentType;
}
