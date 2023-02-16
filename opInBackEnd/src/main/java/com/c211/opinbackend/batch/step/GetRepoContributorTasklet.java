package com.c211.opinbackend.batch.step;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.c211.opinbackend.batch.dto.github.ContributorDto;
import com.c211.opinbackend.batch.service.RepositoryService;
import com.c211.opinbackend.persistence.entity.BatchToken;
import com.c211.opinbackend.persistence.entity.GithubContributor;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryContributor;
import com.c211.opinbackend.persistence.entity.RepositoryGithubContributor;
import com.c211.opinbackend.persistence.repository.BatchTokenRepository;
import com.c211.opinbackend.persistence.repository.GithubContributorRepository;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.persistence.repository.RepoContributorRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.persistence.repository.RepositoryGithubContributorRepository;

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
	private final GithubContributorRepository githubContributorRepository;
	private final RepositoryGithubContributorRepository repositoryGithubContributorRepository;

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
							// contributor 가 우리 멤버인지 확인,
							Member member = memberRepository.findByGithubId(contributor.getId().toString())
								.orElse(null);
							// 우리 멤버라면
							if (member != null) {
								// repository contributor 관계 존재하는지 확인
								// 존재한다면 pass
								// 존재하지 않으면 insert
								RepositoryContributor repositoryContributor = repoContributorRepository.findByMemberAndRepository(
									member, repo).orElse(null);

								if (repositoryContributor == null) {
									RepositoryContributor con = RepositoryContributor
										.builder()
										.repository(contributor.getRepository())
										.member(member)
										.build();

									repoContributorRepository.save(con);
								}
							} else {
								// 우리 멤버가 아니라면 github contributor 에 있는지 보고
								GithubContributor githubContributor = githubContributorRepository.findByAuthorId(
									String.valueOf(contributor.getId())).orElse(null);

								// 있다면
								if (githubContributor != null) {
									// 관계 연결돼 있는지 보고
									RepositoryGithubContributor repositoryGithubContributor = repositoryGithubContributorRepository.findByGithubContributor(
										githubContributor).orElse(null);
									// 관계 없다면
									if (repositoryGithubContributor == null) {
										// 관계 insert
										repositoryGithubContributorRepository.save(
											RepositoryGithubContributor.builder()
												.githubContributor(githubContributor)
												.repository(repo)
												.build()
										);
									}

									// 없다면
								} else {
									GithubContributor con = GithubContributor.builder()
										.authorId(String.valueOf(contributor.getId()))
										.nickname(contributor.getLogin())
										.githubUrl(contributor.getHtmlUrl())
										.avatarUrl(contributor.getAvatarUrl())
										.build();

									// github contributor insert
									githubContributorRepository.save(con);

									// 관계 insert
									repositoryGithubContributorRepository.save(
										RepositoryGithubContributor.builder()
											.githubContributor(con)
											.repository(repo)
											.build()
									);
								}

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
