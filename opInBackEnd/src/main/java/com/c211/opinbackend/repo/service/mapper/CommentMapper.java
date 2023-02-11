package com.c211.opinbackend.repo.service.mapper;

import com.c211.opinbackend.persistence.entity.Comment;
import com.c211.opinbackend.repo.model.response.CommentDetailReponse;

public class CommentMapper {

	public static CommentDetailReponse toDetailCommentDto(Comment comment) {
		return CommentDetailReponse.builder()
			.id(comment.getId())
			.memberAvatarUrl(comment.getMember().getAvatarUrl())
			.memberName(comment.getMember().getNickname())
			.commentContent(comment.getContent())
			.createDate(comment.getCreateDate())
			.updateDate(comment.getUpdateDate())
			.build();

	}
}
