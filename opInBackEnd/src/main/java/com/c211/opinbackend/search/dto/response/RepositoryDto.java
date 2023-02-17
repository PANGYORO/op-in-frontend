package com.c211.opinbackend.search.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.c211.opinbackend.repo.model.contributor.RepositoryContributorDto;
import com.c211.opinbackend.repo.model.response.RepoTechLangDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RepositoryDto {
	private Long id;
	private String title;
	private String content;
	private List<RepoTechLangDto> techLangs;
	private List<RepositoryContributorDto> contributors;
	private Long star;
	private Long forkNum;
	private List<String> topicList;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime updateDate;

	private String htmlUrl;

}
