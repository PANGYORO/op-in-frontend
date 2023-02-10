package com.c211.opinbackend.search.mapper;

import com.c211.opinbackend.persistence.entity.Comment;
import com.c211.opinbackend.search.dto.response.CommentDto;
import com.c211.opinbackend.search.dto.response.MemberDto;

public class CommentMapper {
	public static CommentDto toPostCommentDto(Comment comment) {
		return CommentDto.builder()
			.commentType(comment.getCommentType())
			.member(MemberDto.from(comment.getMember()))
			.comment(comment.getContent())
			.repositoryId(comment.getRepositoryQnA().getRepository().getId())
			.repositoryPostId(comment.getRepositoryPost().getId())
			.build();
	}

	public static CommentDto toQnaCommentDto(Comment comment) {
		return CommentDto.builder()
			.commentType(comment.getCommentType())
			.member(MemberDto.from(comment.getMember()))
			.comment(comment.getContent())
			.repositoryId(comment.getRepositoryQnA().getRepository().getId())
			.repositoryQnaId(comment.getRepositoryQnA().getId())
			.build();
	}
}
