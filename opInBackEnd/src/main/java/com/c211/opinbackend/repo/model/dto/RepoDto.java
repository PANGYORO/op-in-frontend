package com.c211.opinbackend.repo.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@Builder
public class RepoDto {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long repoId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String memberEmail;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String fullName;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String description;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String htmlUrl;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean secret;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean fork;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDateTime createAt;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDateTime updatedAt;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDateTime pushedAt;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long size;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long stargazersCount;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long watcherCount;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean archived;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean disabled;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long forks;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long watchers;

}
