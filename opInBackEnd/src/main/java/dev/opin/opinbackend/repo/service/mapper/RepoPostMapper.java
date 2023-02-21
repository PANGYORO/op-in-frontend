package dev.opin.opinbackend.repo.service.mapper;

import java.util.ArrayList;
import java.util.List;

import dev.opin.opinbackend.persistence.entity.Comment;
import dev.opin.opinbackend.persistence.entity.RepositoryPost;
import dev.opin.opinbackend.repo.model.response.CommentDetailResponse;
import dev.opin.opinbackend.repo.model.response.RepoPostDetailResponse;
import dev.opin.opinbackend.repo.model.response.RepoPostSimpleResponse;

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
		List<CommentDetailResponse> detailCommentList = new ArrayList<>();
		for (Comment comment : repositoryPost.getCommentsList()) {
			detailCommentList.add(CommentMapper.toDetailCommentDto(comment));
		}
		return RepoPostDetailResponse.builder()
			.id(repositoryPost.getId())
			.authorMemberName(repositoryPost.getMember().getNickname())
			.authorMemberAvatar(repositoryPost.getMember().getAvatarUrl())
			.date(repositoryPost.getDate())
			.title(repositoryPost.getTitleContent().getTitle())
			.likeCount(repositoryPost.getLikeList().size())
			.commentCount(repositoryPost.getCommentsList().size())
			.commentList(detailCommentList)
			.content(repositoryPost.getTitleContent().getContent())
			.repoId(repositoryPost.getRepository().getId())
			.repoName(repositoryPost.getRepository().getName())
			.build();
	}
}
