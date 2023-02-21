package dev.opin.opinbackend.batch.dto.mapper;

import org.springframework.stereotype.Component;

import dev.opin.opinbackend.batch.dto.github.CommitDto;
import dev.opin.opinbackend.persistence.entity.CommitHistory;
import dev.opin.opinbackend.persistence.repository.CommitHistoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommitHistoryMapper {

	private final CommitHistoryRepository commitHistoryRepository;

	public CommitHistory toCommitHistory(CommitDto commit) {

		if (commit.getParents() != null & commit.getParents().size() > 0) {
			CommitHistory commitHistory = commitHistoryRepository.findBySha(commit.getParents().get(0).getSha())
				.orElse(null);

			if (commitHistory != null) {
				return CommitHistory.builder()
					.sha(commit.getSha())
					.repository(commit.getRepo())
					.date(commit.getAuthor().getDate())
					.message(commit.getCommit().getMessage())
					.authorId(Long.toString(commit.getAuthor().getId()))
					.authorName(commit.getAuthor().getName())
					.authorAvatarUrl(commit.getAuthor().getAvatarUrl())
					.parent(commitHistory)
					.build();
			}
		}

		return CommitHistory.builder()
			.sha(commit.getSha())
			.repository(commit.getRepo())
			.date(commit.getAuthor().getDate())
			.message(commit.getCommit().getMessage())
			.authorId(Long.toString(commit.getAuthor().getId()))
			.authorName(commit.getAuthor().getName())
			.authorAvatarUrl(commit.getAuthor().getAvatarUrl())
			.build();

	}
}
