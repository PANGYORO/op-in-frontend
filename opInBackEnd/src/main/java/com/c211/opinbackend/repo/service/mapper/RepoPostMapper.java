package com.c211.opinbackend.repo.service.mapper;

import com.c211.opinbackend.persistence.entity.RepositoryPost;
import com.c211.opinbackend.repo.model.response.RepoPostSimpleResponse;

public class RepoPostMapper {
	public static RepoPostSimpleResponse toSimpleResponse(RepositoryPost post) {
		return RepoPostSimpleResponse.builder()
			.authorMemberName(post.getMember().getNickname())
			.authorMemberAvatar(post.getMember().getAvatarUrl())
			.createTime(post.getDate())
			.title(post.getTitleContent().getTitle())
			.likeCount(post.getLikeList().size())
			.commentCount(post.getCommentsList().size())
			.build();

	}
}
