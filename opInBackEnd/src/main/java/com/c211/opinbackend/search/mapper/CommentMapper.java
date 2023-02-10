package com.c211.opinbackend.search.mapper;

import com.c211.opinbackend.persistence.entity.Comment;
import com.c211.opinbackend.search.dto.response.CommentDto;

public class CommentMapper {
	public static CommentDto toPostCommentDto(Comment comment) {
		return CommentDto.builder()
			.commentType(comment.getCommentType())
			.memberEmail(comment.getMember().getEmail())
			.comment(comment.getContent())
			.repositoryId(comment.getRepositoryQnA().getRepository().getId())
			.repositoryPostId(comment.getRepositoryPost().getId())
			.build();
	}

	public static CommentDto toQnaCommentDto(Comment comment) {
		return CommentDto.builder()
			.commentType(comment.getCommentType())
			.memberEmail(comment.getMember().getEmail())
			.comment(comment.getContent())
			.repositoryId(comment.getRepositoryQnA().getRepository().getId())
			.repositoryQnaId(comment.getRepositoryQnA().getId())
			.build();
	}
}
