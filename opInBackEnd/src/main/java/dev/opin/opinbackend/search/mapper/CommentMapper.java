package dev.opin.opinbackend.search.mapper;

import dev.opin.opinbackend.persistence.entity.Comment;
import dev.opin.opinbackend.search.dto.response.CommentDto;
import dev.opin.opinbackend.search.dto.response.MemberDto;

public class CommentMapper {
	public static CommentDto toPostCommentDto(Comment comment) {
		return CommentDto.builder()
			.commentType(comment.getCommentType())
			.member(MemberDto.from(comment.getMember()))
			.comment(comment.getContent())
			.repositoryId(comment.getRepositoryPost().getRepository().getId())
			.repositoryPostId(comment.getRepositoryPost().getId())
			.createDate(comment.getCreateDate())
			.updateDate(comment.getUpdateDate())
			.build();
	}

	public static CommentDto toQnaCommentDto(Comment comment) {
		return CommentDto.builder()
			.commentType(comment.getCommentType())
			.member(MemberDto.from(comment.getMember()))
			.comment(comment.getContent())
			.repositoryId(comment.getRepositoryQnA().getRepository().getId())
			.repositoryQnaId(comment.getRepositoryQnA().getId())
			.createDate(comment.getCreateDate())
			.updateDate(comment.getUpdateDate())
			.build();
	}
}
