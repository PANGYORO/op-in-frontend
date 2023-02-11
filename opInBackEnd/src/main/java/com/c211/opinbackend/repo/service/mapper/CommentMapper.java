package com.c211.opinbackend.repo.service.mapper;

import java.util.ArrayList;

import com.c211.opinbackend.persistence.entity.Comment;
import com.c211.opinbackend.persistence.entity.RepositoryQnA;
import com.c211.opinbackend.repo.model.response.CommentDetailResponse;
import com.c211.opinbackend.repo.model.response.RepoQnAResponse;

public class CommentMapper {

	public static CommentDetailResponse toDetailCommentDto(Comment comment) {
		return CommentDetailResponse.builder()
			.commentId(comment.getId())
			.memberAvatarUrl(comment.getMember().getAvatarUrl())
			.memberName(comment.getMember().getNickname())
			.commentContent(comment.getContent())
			.createDate(comment.getCreateDate())
			.updateDate(comment.getUpdateDate())
			.build();
	}

	public static CommentDetailResponse toRepoCommentDetailResponse(Comment comment) {
		return CommentDetailResponse.builder()
			.qnaId(comment.getId())
			.memberName(comment.getMember().getNickname())
			.commentContent(comment.getContent())
			.createDate(comment.getCreateDate())
			.updateDate(comment.getUpdateDate())
			.build();
	}

	public static RepoQnAResponse toRepoQnAResponse(RepositoryQnA repoQnA) {
		return RepoQnAResponse.builder()
			.qnaId(repoQnA.getId())
			.authorAvatar(repoQnA.getAuthorMember().getAvatarUrl())
			.authorMember(repoQnA.getAuthorMember().getNickname())
			.content(repoQnA.getContent())
			.createTime(repoQnA.getCreateTime())
			.qnACommentList(new ArrayList<>())
			.build();
	}

}
