package com.c211.opinbackend.repo.service.mapper;

import java.util.ArrayList;
import java.util.List;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.entity.RepositoryContributor;
import com.c211.opinbackend.repo.entitiy.Repository;
import com.c211.opinbackend.repo.entitiy.RepositoryTechLanguage;
import com.c211.opinbackend.repo.entitiy.RepositoryTopic;
import com.c211.opinbackend.repo.model.contributor.RepositoryContributorDto;
import com.c211.opinbackend.repo.model.repository.RepoDto;
import com.c211.opinbackend.repo.model.response.RepositoryResponseDto;
import com.c211.opinbackend.repo.model.response.repoTechLang.RepoTechLangDTO;

public class RepoMapper {

	public static RepositoryResponseDto toDto(Repository repository) {
		List<RepoTechLangDTO> repoTechLangDTOList = getRepoTechLangDtoList(repository);
		List<RepositoryContributorDto> repositoryContributorDtoList = getRepoTechContributorDtoList(repository);
		List<String> topics = getTopicList(repository);
		RepositoryResponseDto repositoryResponseDto = RepositoryResponseDto.builder()
			.id(repository.getId())
			.title(repository.getName())
			.content(repository.getName())
			.techLangs(repoTechLangDTOList)
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

	public static Repository toEntity(Member member, RepoDto repoDto) {
		Repository repositoryToSave = Repository
			.builder()
			.member(member)
			.name(repoDto.getName())
			.fullName(repoDto.getFullName())
			.githubAddress(repoDto.getGithubAddress())
			.description(repoDto.getGithubAddress())
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
		return repositoryToSave;
	}
}
