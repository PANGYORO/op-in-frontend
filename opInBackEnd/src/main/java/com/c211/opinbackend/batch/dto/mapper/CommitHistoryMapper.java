package com.c211.opinbackend.batch.dto.mapper;

import com.c211.opinbackend.batch.dto.github.CommitDto;
import com.c211.opinbackend.persistence.entity.CommitHistory;

public class CommitHistoryMapper {

	public static CommitHistory toCommitHistory(CommitDto commit) {
		return CommitHistory.builder()
			.sha(commit.getSha())
			.repository(commit.getRepo())
			.date(commit.getAuthor().getDate())
			.message(commit.getCommit().getMessage())
			.authorId(Long.toString(commit.getAuthor().getId()))
			.authorName(commit.getAuthor().getName())
			.authorAvatarUrl(commit.getAuthor().getAvatarUrl())
			.parent(toCommitHistory(commit.getParents().get(0)))
			.build();
	}
}
