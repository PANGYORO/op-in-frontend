package com.c211.opinbackend.batch.dto;

import org.springframework.stereotype.Component;

import com.c211.opinbackend.batch.dto.github.CommitDto;
import com.c211.opinbackend.batch.dto.github.ContributorDto;
import com.c211.opinbackend.batch.dto.github.PullRequestDto;
import com.c211.opinbackend.batch.dto.github.RepositoryDto;

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
