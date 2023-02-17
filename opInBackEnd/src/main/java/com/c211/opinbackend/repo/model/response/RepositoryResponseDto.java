package com.c211.opinbackend.repo.model.response;

import java.time.LocalDate;
import java.util.List;

import com.c211.opinbackend.repo.model.contributor.RepositoryContributorDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RepositoryResponseDto {
	private Long id;
	private Long ownerId;
	private String title;
	private String content;
	private List<RepoTechLangDto> techLangs;
	private List<RepositoryContributorDto> contributors;
	private List<RepositoryContributorDto> gitContributors;
	private Long star;
	private Long forkNum;
	private List<String> topicList;
	private LocalDate updateDate;

}