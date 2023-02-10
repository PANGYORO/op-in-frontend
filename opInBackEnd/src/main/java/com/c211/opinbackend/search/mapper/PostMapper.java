package com.c211.opinbackend.search.mapper;

import java.util.stream.Collectors;

import com.c211.opinbackend.persistence.entity.RepositoryPost;
import com.c211.opinbackend.search.dto.response.SearchPostDto;

public class PostMapper {
	public static SearchPostDto toSearchPostDto(RepositoryPost post) {
		return SearchPostDto.builder()
			.id(post.getId())
			.title(post.getTitleContent().getTitle())
			.content(post.getTitleContent().getContent())
			.comments(post.getCommentsList()
				.stream()
				.map(comment -> CommentMapper.toPostCommentDto(comment))
				.collect(Collectors.toList())
			)
			.commentCount(post.getCommentsList().size())
			.likeCount(post.getLikeList().size())
			.imageUrl(post.getImageUrl())
			.mergeFl(post.getMergeFL())
			.closeState(post.getCloseState())
			.date(post.getDate())
			.build();
	}
}
