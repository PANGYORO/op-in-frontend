package com.c211.opinbackend.repo.service.mapper;

import java.util.ArrayList;

import com.c211.opinbackend.persistence.entity.Comment;
import com.c211.opinbackend.repo.model.response.CommentDetailReponse;
import com.c211.opinbackend.repo.model.response.RepoQnAResponse;

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

	public static RepoQnAResponse toRepoQnAResponse(Comment comment) {
		return RepoQnAResponse.builder()
			.qnaId(comment.getId())
			.authorAvatar(comment.getMember().getAvatarUrl())
			.authorMember(comment.getMember().getNickname())
			.content(comment.getContent())
			.createTime(comment.getCreateDate())
			.qnACommentList(new ArrayList<>())
			.build();
	}

}
