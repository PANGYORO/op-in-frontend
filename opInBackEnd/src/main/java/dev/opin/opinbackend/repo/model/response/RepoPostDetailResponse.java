package dev.opin.opinbackend.repo.model.response;

import java.util.List;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class RepoPostDetailResponse extends RepoPostSimpleResponse {
	private List<CommentDetailResponse> commentList;
	private String content;
	private Long repoId;
	private String repoName;

}
