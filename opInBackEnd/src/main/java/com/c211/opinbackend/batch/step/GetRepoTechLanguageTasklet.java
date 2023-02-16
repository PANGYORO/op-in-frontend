package com.c211.opinbackend.batch.step;

import java.util.List;
import java.util.Map;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.c211.opinbackend.persistence.entity.BatchToken;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryTechLanguage;
import com.c211.opinbackend.persistence.entity.TechLanguage;
import com.c211.opinbackend.persistence.repository.BatchTokenRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.persistence.repository.RepoTechLanguageRepository;
import com.c211.opinbackend.persistence.repository.TechLanguageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetRepoTechLanguageTasklet implements Tasklet {
	private final BatchTokenRepository batchTokenRepository;
	private final RepoRepository repoRepository;
	private final Action action;
	private final TechLanguageRepository techLanguageRepository;
	private final RepoTechLanguageRepository repoTechLanguageRepository;

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
					Map<String, Long> languages = action.getRepositoryLanguages(githubToken, repo.getFullName(),
						String.valueOf(page));

					for (String lan : languages.keySet()) {
						lan = lan.toUpperCase();

						try {
							TechLanguage techLanguage = techLanguageRepository.findByTitle(lan)
								.orElse(null);

							if (techLanguage == null) {
								techLanguage = techLanguageRepository.save(
									TechLanguage.builder().title(lan).build());
							}

							RepositoryTechLanguage repoTechRelation = repoTechLanguageRepository.findByRepositoryAndTechLanguage(
								repo, techLanguage).orElse(null);

							if (repoTechRelation == null) {
								repoTechLanguageRepository.save(RepositoryTechLanguage.builder()
									.techLanguage(techLanguage)
									.repository(repo)
									.build());
							}
						} catch (Exception e) {
							log.info(e.toString());
						}
					}

					if (languages.size() < 100) {
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
