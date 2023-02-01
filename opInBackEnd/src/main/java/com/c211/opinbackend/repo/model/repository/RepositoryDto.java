package com.c211.opinbackend.repo.model.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class RepositoryDto {

	private String title;
	private String content;

	private RepositoryDto repositoryDto;

}
