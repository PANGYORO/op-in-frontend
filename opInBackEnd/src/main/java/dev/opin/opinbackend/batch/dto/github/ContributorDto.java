package dev.opin.opinbackend.batch.dto.github;

import dev.opin.opinbackend.persistence.entity.Repository;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContributorDto {
	private String login;
	private Long id;
	@JsonProperty("avatar_url")
	private String avatarUrl;

	private String type;

	@JsonProperty("url")
	private String userInfoUrl;

	@JsonProperty("html_url")
	private String htmlUrl;

	@JsonProperty("followers_url")
	private String followersUrl;

	@JsonProperty("following_url")
	private String followingUrl;

	@JsonProperty("repos_url")
	private String reposUrl;

	public Long contributions;

	private Repository repository;
}
