package dev.opin.opinbackend.batch.dto.github;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
	private String login;
	private Long id;
	@JsonProperty("avatar_url")
	private String avatarUrl;

	private String type;

	@JsonProperty("url")
	private String userInfoUrl;

	@JsonProperty("followers_url")
	private String followersUrl;

	@JsonProperty("following_url")
	private String followingUrl;

	@JsonProperty("repos_url")
	private String reposUrl;

	@JsonProperty("public_repos")
	private Long publicRepos;

	@JsonProperty("created_at")
	private LocalDateTime createdAt;

	@JsonProperty("updated_at")
	private LocalDateTime updatedAt;

	@JsonProperty("date")
	private LocalDateTime date;

	@JsonProperty("name")
	private String name;
}
