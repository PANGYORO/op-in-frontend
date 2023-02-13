package com.c211.opinbackend.repo.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentSimpleResponse {
	private Long id;
	private String memberName;
	private String memberAvatarUrl;
	private String commentContent;
}
