package com.c211.opinbackend.batch.step;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.c211.opinbackend.batch.dto.mapper.PullRequestMapper;
import com.c211.opinbackend.persistence.entity.BatchToken;
import com.c211.opinbackend.persistence.entity.PullRequest;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.repository.BatchTokenRepository;
import com.c211.opinbackend.persistence.repository.PullRequestRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetRepoPullRequestTasklet implements Tasklet {
	private final BatchTokenRepository batchTokenRepository;
	private final RepoRepository repoRepository;
	private final Action action;
	private final PullRequestRepository pullRequestRepository;
	private final PullRequestMapper pullRequestMapper;

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
					PullRequest[] pullRequests = Arrays.stream(
						action.getRepositoryPulls(githubToken, repo.getFullName(), String.valueOf(page))).map(
						prDto -> pullRequestMapper.toPullRequest(prDto, repo)
					).toArray(PullRequest[]::new);

					for (PullRequest pullReque : pullRequests) {
						try {
							pullRequestRepository.save(pullReque);
						} catch (Exception e) {
							log.info(e.toString());
						}
					}

					if (pullRequests.length < 100) {
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
