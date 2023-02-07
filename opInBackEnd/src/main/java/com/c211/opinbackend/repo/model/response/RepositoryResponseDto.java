package com.c211.opinbackend.repo.model.response;

import java.time.LocalDate;
import java.util.List;

import com.c211.opinbackend.repo.model.contributor.RepositoryContributorDto;
import com.c211.opinbackend.repo.model.response.repoTechLang.RepoTechLangDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RepositoryResponseDto {
	private Long id;
	private String title;
	private String content;
	private List<RepoTechLangDTO> techLangs;
	private List<RepositoryContributorDto> contributors;
	private List<RepositoryContributorDto> gitContributors;
	private String star;
	private String forkNum;
	private List<String> topicList;
	private LocalDate updateDate;

}
