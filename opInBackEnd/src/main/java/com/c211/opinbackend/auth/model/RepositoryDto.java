package com.c211.opinbackend.auth.model;

import java.time.LocalDateTime;
import java.util.List;

import com.c211.opinbackend.batch.dto.github.LicenseDto;
import com.c211.opinbackend.batch.dto.github.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Github API를 통해 가져온 Repository 입니다.
 */
@Getter
@Setter
@ToString
public class RepositoryDto {
	private Long id;
	@JsonProperty("node_id")
	private String nodeId;

	private String name;

	@JsonProperty("full_name")
	private String fullName;

	private UserDto owner;

	private String description;
	@JsonProperty("html_url")
	private String htmlUrl;

	@JsonProperty("private")
	private boolean secret;
	private boolean fork;

	@JsonProperty("created_at")
	private LocalDateTime createdAt;
	@JsonProperty("updated_at")
	private LocalDateTime updatedAt;
	@JsonProperty("pushed_at")
	private LocalDateTime pushedAt;

	private Long size;

	@JsonProperty("stargazers_count")
	private Long stargazersCount;
	@JsonProperty("watchers_count")
	private Long watchersCount;

	private boolean archived;
	private boolean disabled;

	private Long forks;
	private Long watchers;

	private LicenseDto license;

	private List<String> topics;

	@JsonProperty("languages_url")
	private String languagesUrl;

	@JsonProperty("branches_url")
	private String branchesUrl;

	@JsonProperty("commits_url")
	private String commitsUrl;

}
