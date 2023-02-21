package dev.opin.opinbackend.batch.dto;

import dev.opin.opinbackend.batch.dto.github.CommitDto;
import dev.opin.opinbackend.batch.dto.github.ContributorDto;
import dev.opin.opinbackend.batch.dto.github.PullRequestDto;
import dev.opin.opinbackend.batch.dto.github.RepositoryDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MonoDto {

	private RepositoryDto[] repositorys;
	private String[] languages;
	private CommitDto[] commits;
	private PullRequestDto[] pullReques;
	private ContributorDto[] contributors;
}
