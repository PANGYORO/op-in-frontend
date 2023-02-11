package com.c211.opinbackend.repo.service.mapper;

import java.util.ArrayList;

import com.c211.opinbackend.persistence.entity.Comment;
import com.c211.opinbackend.repo.model.response.CommentDetailResponse;
import com.c211.opinbackend.repo.model.response.RepoQnAResponse;

public class CommentMapper {

	public static CommentDetailResponse toDetailCommentDto(Comment comment) {
		return CommentDetailResponse.builder()
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

	public static CommentDetailResponse toSaveQnAResponse(Comment comment) {
		return CommentDetailResponse.builder()
			.qnaId(comment.getId())
			.memberAvatarUrl(comment.getMember().getAvatarUrl())
			.memberName(comment.getMember().getNickname())
			.commentContent(comment.getContent())
			.createDate(comment.getCreateDate())
			.updateDate(comment.getUpdateDate())
			.build();
	}

}
