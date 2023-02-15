package com.c211.opinbackend.batch.step;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.c211.opinbackend.batch.dto.github.ContributorDto;
import com.c211.opinbackend.persistence.entity.BatchToken;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryContributor;
import com.c211.opinbackend.persistence.repository.BatchTokenRepository;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.persistence.repository.RepoContributorRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetRepoContributorTasklet implements Tasklet {
	private final BatchTokenRepository batchTokenRepository;
	private final RepoRepository repoRepository;
	private final Action action;
	private final MemberRepository memberRepository;
	private final RepoContributorRepository repoContributorRepository;

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
					ContributorDto[] contributorDtos = action.getContributors(githubToken, repo.getFullName(),
						String.valueOf(page));

					for (ContributorDto contributor : contributorDtos) {
						try {
							Member member = memberRepository.findByGithubId(contributor.getId().toString())
								.orElse(null);
							if (member != null) {
								RepositoryContributor con = RepositoryContributor
									.builder()
									.repository(contributor.getRepository())
									.member(member)
									.build();

								repoContributorRepository.save(con);
							}
						} catch (Exception e) {
							log.info(e.toString());
						}
					}

					if (contributorDtos.length < 100) {
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
