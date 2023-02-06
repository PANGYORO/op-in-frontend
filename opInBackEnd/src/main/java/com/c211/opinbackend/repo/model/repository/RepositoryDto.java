package com.c211.opinbackend.repo.model.repository;

import java.time.LocalDateTime;

import com.c211.opinbackend.auth.model.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RepositoryDto {
	private MemberDto memberDto;
	private String name;
	private String githubAddress;
	private String fullName;
	private String htmlUrl;
	private String secret;
	private String fork;
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
	private String language;

}
