package com.c211.opinbackend.repo.service.mapper;

import com.c211.opinbackend.repo.entitiy.Repository;
import com.c211.opinbackend.repo.model.repository.RepositoryDto;

public class RepoMapper {

	public static RepositoryDto toDto(Repository repository) {
		RepositoryDto repositoryDto = RepositoryDto.builder()
			.id(repository.getId())
			.title(repository.getTitleContent().getTitle())
			.content(repository.getTitleContent().getContent())
			.build();
		return repositoryDto;
	}
}
