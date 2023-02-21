package dev.opin.opinbackend.repo.model.requeset;

import lombok.Getter;

@Getter
public class RequestCommentCreateToPost {
	private Long postId;
	private String commentContent;
}
