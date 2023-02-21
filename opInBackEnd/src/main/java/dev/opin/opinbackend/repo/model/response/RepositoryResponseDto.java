package dev.opin.opinbackend.repo.model.response;

import java.time.LocalDate;
import java.util.List;

import dev.opin.opinbackend.repo.model.contributor.RepositoryContributorDto;

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
