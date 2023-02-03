package com.c211.opinbackend.repo.service.mapper;

import java.util.ArrayList;
import java.util.List;

import com.c211.opinbackend.auth.entity.RepositoryContributor;
import com.c211.opinbackend.repo.entitiy.Repository;
import com.c211.opinbackend.repo.entitiy.RepositoryTechLanguage;
import com.c211.opinbackend.repo.entitiy.RepositoryTopic;
import com.c211.opinbackend.repo.model.contributor.RepositoryContributorDto;
import com.c211.opinbackend.repo.model.repoTechLang.RepoTechLangDTO;
import com.c211.opinbackend.repo.model.repository.RepositoryDto;

public class RepoMapper {

	public static RepositoryDto toDto(Repository repository) {
		List<RepoTechLangDTO> repoTechLangDTOList = getRepoTechLangDtoList(repository);
		List<RepositoryContributorDto> repositoryContributorDtoList = getRepoTechContributorDtoList(repository);
		List<String> topics = getTopicList(repository);
		RepositoryDto repositoryDto = RepositoryDto.builder()
			.id(repository.getId())
			.title(repository.getTitleContent().getTitle())
			.content(repository.getTitleContent().getContent())
			.techLangList(repoTechLangDTOList)
			.repositoryContributorDtoList(repositoryContributorDtoList)
			.star("1233455565")
			.forkNum("123214141")
			.topicList(topics)
			.build();
		return repositoryDto;
	}

	private static List<String> getTopicList(Repository repository) {
		List<String> res = new ArrayList<>();
		List<RepositoryTopic> repositoryTopicList = repository.getTopicList();
		for (RepositoryTopic topic : repositoryTopicList) {
			res.add(topic.getTopic().getTitle());
			// TODO: 2023-02-03 이거 for문 만큼 요청하는데 맞는지 판단하자
		}
		return res;

	}

	private static List<RepositoryContributorDto> getRepoTechContributorDtoList(Repository repository) {
		List<RepositoryContributorDto> res = new ArrayList<>();
		List<RepositoryContributor> contributorEntityList = repository.getRepositoryContributorList();
		for (RepositoryContributor contributorEntity : contributorEntityList) {
			res.add(RepositoryContributorDto.builder()
				.id(String.valueOf(contributorEntity.getId()))
				.nickname(contributorEntity.getMember().getNickname())
				.profileImg(contributorEntity.getMember().getAvatarUrl())
				.build());
		}
		return res;
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
