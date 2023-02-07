package com.c211.opinbackend.repo.model.dto;

import com.c211.opinbackend.persistence.entity.CommentType;

public class CommentDto {
	private Long memberEmail;
	private Long repositoryId;
	private Long repositoryPostId;
	private String comment;
	private CommentType commentType;
}
