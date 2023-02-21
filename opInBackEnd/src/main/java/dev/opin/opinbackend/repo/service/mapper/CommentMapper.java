package dev.opin.opinbackend.repo.service.mapper;

import java.util.ArrayList;

import dev.opin.opinbackend.persistence.entity.Comment;
import dev.opin.opinbackend.persistence.entity.RepositoryQnA;
import dev.opin.opinbackend.repo.model.response.CommentDetailResponse;
import dev.opin.opinbackend.repo.model.response.RepoQnAResponse;

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
			.qnaId(comment.getRepositoryQnA().getId())
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
