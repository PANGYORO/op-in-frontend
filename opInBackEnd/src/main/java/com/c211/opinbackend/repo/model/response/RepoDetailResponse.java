package com.c211.opinbackend.repo.model.response;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RepoDetailResponse {
	private Long id;
	private String title;
	private String content;
	private List<RepoTechLangDto> techLangs;
	private Long star;
	private Long forkNum;
	private List<String> topicList;
	private LocalDate updateDate;

}
