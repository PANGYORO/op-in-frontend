package dev.opin.opinbackend.batch.dto.opin;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class GitHubMemberDto {
	private Long id;
	private String githubToken;
	private String githubId;
	private String githubUserName;
}
