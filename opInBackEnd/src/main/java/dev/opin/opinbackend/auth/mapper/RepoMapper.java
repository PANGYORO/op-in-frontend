package dev.opin.opinbackend.auth.mapper;

import dev.opin.opinbackend.auth.model.RepositoryDto;
import dev.opin.opinbackend.persistence.entity.Member;
import dev.opin.opinbackend.persistence.entity.Repository;

public class RepoMapper {
	public static Repository toRepository(RepositoryDto repoDto, Member member) {
		return new Repository().builder()
			.id(repoDto.getId())
			.member(member)
			.name(repoDto.getName())
			.fullName(repoDto.getFullName())
			.description(repoDto.getDescription())
			.htmlUrl(repoDto.getHtmlUrl())
			.secret(repoDto.isSecret())
			.fork(repoDto.isFork())
			.createdAt(repoDto.getCreatedAt())
			.updatedAt(repoDto.getUpdatedAt())
			.pushedAt(repoDto.getPushedAt())
			.size(repoDto.getSize())
			.stargazersCount(repoDto.getStargazersCount())
			.watchersCount(repoDto.getWatchersCount())
			.archived(repoDto.isArchived())
			.disabled(repoDto.isDisabled())
			.forks(repoDto.getForks())
			.watchers(repoDto.getWatchers())
			.build();
	}
}
