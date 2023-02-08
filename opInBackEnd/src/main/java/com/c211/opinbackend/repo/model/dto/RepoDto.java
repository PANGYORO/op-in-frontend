package com.c211.opinbackend.repo.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class RepoDto {
	private String memberEmail;
	private String name;
	private String fullName;
	private String description;
	private String htmlUrl;
	private Boolean secret;
	private Boolean fork;
	private LocalDateTime createAt;
	private LocalDateTime updatedAt;
	private LocalDateTime pushedAt;
	private Long size;
	private Long stargazersCount;
	private Long watcherCount;
	private Boolean archived;
	private Boolean disabled;
	private Long forks;
	private Long watchers;

}
