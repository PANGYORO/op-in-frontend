package com.c211.opinbackend.repo.service.mapper;

import java.util.ArrayList;
import java.util.List;

import com.c211.opinbackend.repo.entitiy.Repository;
import com.c211.opinbackend.repo.entitiy.RepositoryTechLanguage;
import com.c211.opinbackend.repo.model.repoTechLang.RepoTechLangDTO;
import com.c211.opinbackend.repo.model.repository.RepositoryDto;

public class RepoMapper {

	public static RepositoryDto toDto(Repository repository) {
		List<RepoTechLangDTO> repoTechLangDTOList = getRepoTechLangDtoList(
			repository);

		RepositoryDto repositoryDto = RepositoryDto.builder()
			.id(repository.getId())
			.title(repository.getTitleContent().getTitle())
			.content(repository.getTitleContent().getContent())
			.techLangList(repoTechLangDTOList)
			.content(null) // TODO: 2023-02-02  컨텐트 긁어오기 
			.build();
		return repositoryDto;
	}

	private static List<RepoTechLangDTO> getRepoTechLangDtoList(Repository repository) {
		List<RepositoryTechLanguage> repositoryTechLanguages = repository.getRepositoryTechLanguages();
		List<RepoTechLangDTO> repoTechLangDtoList = new ArrayList<>();
		for (RepositoryTechLanguage language : repositoryTechLanguages) {
			repoTechLangDtoList.add(RepoTechLangDTO.builder()
				.title(language.getTechLanguage().getTitle())
				.color(language.getTechLanguage().getColor())
				.build());
		}
		return repoTechLangDtoList;

	}
}
