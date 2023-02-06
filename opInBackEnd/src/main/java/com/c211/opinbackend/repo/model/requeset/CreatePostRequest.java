package com.c211.opinbackend.repo.model.requeset;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreatePostRequest {
	private String title;
	private String content;

}
