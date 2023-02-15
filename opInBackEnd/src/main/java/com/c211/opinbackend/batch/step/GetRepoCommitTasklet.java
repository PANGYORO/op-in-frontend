package com.c211.opinbackend.batch.step;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.c211.opinbackend.batch.dto.github.CommitDto;
import com.c211.opinbackend.batch.dto.mapper.CommitHistoryMapper;
import com.c211.opinbackend.persistence.entity.BatchToken;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.repository.BatchTokenRepository;
import com.c211.opinbackend.persistence.repository.CommitHistoryRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetRepoCommitTasklet implements Tasklet {
	private final BatchTokenRepository batchTokenRepository;
	private final RepoRepository repoRepository;
	private final Action action;
	private final CommitHistoryMapper commitHistoryMapper;
	private final CommitHistoryRepository commitHistoryRepository;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// token 초기화
		List<BatchToken> batchTokens = batchTokenRepository.findAll();
		int index = 0;
		String githubToken = batchTokens.get(index).getAccessToken();

		List<Repository> repos = repoRepository.findAll();
		for (Repository repo : repos) {

			int page = 1;

			while (true) {
				try {
					CommitDto[] commits = action.getRepositoryCommits(githubToken, repo.getFullName(),
						String.valueOf(page));

					for (CommitDto commit : commits) {
						commit.setRepo(repo);
						try {
							if (commitHistoryRepository.findBySha(commit.getSha()).orElse(null) == null) {
								commitHistoryRepository.save(commitHistoryMapper.toCommitHistory(commit));
							}
						} catch (Exception e) {
							log.info(e.toString());
						}
					}

					if (commits.length < 100) {
						break;
					}

					page += 1;
				} catch (Exception e) {
					index += 1;
					if (batchTokens.size() <= index) {
						return RepeatStatus.FINISHED;
					}
					githubToken = batchTokens.get(index).getAccessToken();
				}
			}

		}
		return RepeatStatus.FINISHED;
	}
}
