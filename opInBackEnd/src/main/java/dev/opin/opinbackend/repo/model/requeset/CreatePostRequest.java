package dev.opin.opinbackend.repo.model.requeset;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreatePostRequest {
	private Long repositoryId;
	private String title;
	private String content;
}
