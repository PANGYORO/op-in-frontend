package com.c211.opinbackend.repo.model.response;

import java.time.LocalDateTime;
import java.util.List;

import com.c211.opinbackend.repo.model.contributor.RepositoryContributorDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RepoDetailResponse {
	private Long id;
	private Long ownerId;
	private String title;
	private String content;
	private List<RepoTechLangDto> techLangs;
	private List<RepositoryContributorDto> contributors;
	private Long star;
	private Long forkNum;
	private List<String> topicList;
	private String html;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	// update day 입니다.
	private LocalDateTime date;

}
