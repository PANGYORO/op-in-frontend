package com.c211.opinbackend.repo.service.mapper;

import java.util.ArrayList;
import java.util.List;

import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryContributor;
import com.c211.opinbackend.persistence.entity.RepositoryTechLanguage;
import com.c211.opinbackend.persistence.entity.RepositoryTopic;
import com.c211.opinbackend.repo.model.contributor.RepositoryContributorDto;
import com.c211.opinbackend.repo.model.dto.RepoDto;
import com.c211.opinbackend.repo.model.response.RepositoryResponseDto;
import com.c211.opinbackend.repo.model.response.repoTechLang.RepoTechLangDto;

public class RepoMapper {
	// TODO: 2023-02-07  to 명진 역활에 따른 이름을 구분 부탁
	public static RepositoryResponseDto toMyRepoDto(Repository repository) {
		List<RepoTechLangDto> repoTechLangDtoList = getRepoTechLangDtoList(repository);
		List<RepositoryContributorDto> repositoryContributorDtoList = getRepoTechContributorDtoList(repository);
		List<String> topics = getTopicList(repository);
		RepositoryResponseDto repositoryResponseDto = RepositoryResponseDto.builder()
			.id(repository.getId())
			.title(repository.getName())
			.content(repository.getName())
			.techLangs(repoTechLangDtoList)
			.contributors(repositoryContributorDtoList)
			.star("1233455565")
			.forkNum("123214141")
			.topicList(topics)
			.gitContributors(null)
			.build();
		return repositoryResponseDto;
	}

	private static List<String> getTopicList(Repository repository) {
		List<String> res = new ArrayList<>();
		List<RepositoryTopic> repositoryTopicList = repository.getTopicList();
		for (RepositoryTopic topic : repositoryTopicList) {
			res.add(topic.getTopic().getTitle());
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

	private static List<RepoTechLangDto> getRepoTechLangDtoList(Repository repository) {
		List<RepositoryTechLanguage> repositoryTechLanguages = repository.getRepositoryTechLanguages();
		List<RepoTechLangDto> repoTechLangDtoList = new ArrayList<>();
		for (RepositoryTechLanguage language : repositoryTechLanguages) {
			repoTechLangDtoList.add(RepoTechLangDto.builder()
				.title(language.getTechLanguage().getTitle())
				.color(language.getTechLanguage().getColor())
				.count(language.getCount())
				.build());
		}
		return repoTechLangDtoList;

	}

	public static Repository toEntity(Member member, RepoDto repoDto) {
		return Repository
			.builder()
			.member(member)
			.name(repoDto.getName())
			.fullName(repoDto.getFullName())
			.htmlUrl(repoDto.getHtmlUrl())
			.description(repoDto.getDescription())
			.htmlUrl(repoDto.getHtmlUrl())
			.secret(repoDto.getSecret())
			.fork(repoDto.getFork())
			.createdAt(repoDto.getCreateAt())
			.updatedAt(repoDto.getUpdatedAt())
			.pushedAt(repoDto.getPushedAt())
			.size(repoDto.getSize())
			.stargazersCount(repoDto.getStargazersCount())
			.watchersCount(repoDto.getWatcherCount())
			.archived(repoDto.getArchived())
			.disabled(repoDto.getDisabled())
			.forks(repoDto.getForks())
			.watchers(repoDto.getWatchers()).build();
	}
}
