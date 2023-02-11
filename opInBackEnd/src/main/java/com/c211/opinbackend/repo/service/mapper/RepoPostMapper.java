package com.c211.opinbackend.repo.service.mapper;

import java.util.ArrayList;
import java.util.List;

import com.c211.opinbackend.persistence.entity.Comment;
import com.c211.opinbackend.persistence.entity.RepositoryPost;
import com.c211.opinbackend.repo.model.response.CommentSimpleResponse;
import com.c211.opinbackend.repo.model.response.RepoPostDetailResponse;
import com.c211.opinbackend.repo.model.response.RepoPostSimpleResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RepoPostMapper {
	public static RepoPostSimpleResponse toSimpleResponse(RepositoryPost post) {
		return RepoPostSimpleResponse.builder()
			.id(post.getId())
			.authorMemberName(post.getMember().getNickname())
			.authorMemberAvatar(post.getMember().getAvatarUrl())
			.date(post.getDate())
			.title(post.getTitleContent().getTitle())
			.likeCount(post.getLikeList().size())
			.commentCount(post.getCommentsList().size())
			.mergeFl(post.getMergeFL())
			.closeState(post.getCloseState())
			.build();

	}

	public static RepoPostDetailResponse toDetailResponse(RepositoryPost repositoryPost) {
		List<CommentSimpleResponse> simpleCommentList = new ArrayList<>();
		for (Comment comment : repositoryPost.getCommentsList()) {
			CommentSimpleResponse commentResDto = CommentSimpleResponse.builder()
				.memberName(comment.getMember().getNickname())
				.memberAvatarUrl(comment.getMember().getAvatarUrl())
				.commentContent(comment.getContent())
				.build();
			simpleCommentList.add(commentResDto);
		}
		return RepoPostDetailResponse.builder()
			.id(repositoryPost.getId())
			.authorMemberName(repositoryPost.getMember().getNickname())
			.authorMemberAvatar(repositoryPost.getMember().getAvatarUrl())
			.date(repositoryPost.getDate())
			.title(repositoryPost.getTitleContent().getTitle())
			.likeCount(repositoryPost.getLikeList().size())
			.commentCount(repositoryPost.getCommentsList().size())
			.commentList(simpleCommentList)
			.content(repositoryPost.getTitleContent().getContent())
			.build();
	}
}
