package com.c211.opinbackend.repo.model.response;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RepositoryResponseSimpleDto {
	private Long id;
	private Long ownerId;
	private String title;
	private String content;
	private List<RepoTechLangDto> techLangs;

	private Long star;
	private Long forkNum;
	private LocalDate updateDate;
}
