package com.c211.opinbackend.batch.step;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.MemberTechLanguage;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryTechLanguage;
import com.c211.opinbackend.persistence.entity.TechLanguage;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.persistence.repository.MemberTechLanguageRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.persistence.repository.RepoTechLanguageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetMemberTechLanguageTasklet implements Tasklet {

	private final MemberTechLanguageRepository memberTechLanguageRepository;
	private final RepoRepository repoRepository;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		List<Repository> repositories = repoRepository.findByMemberNotNull();

		if (repositories != null && repositories.size() > 0) {
			for (Repository repo : repositories) {

				List<RepositoryTechLanguage> repositoryTechLanguages = repo.getRepositoryTechLanguages();
				if (repositoryTechLanguages != null && repositoryTechLanguages.size() > 0) {
					for (RepositoryTechLanguage repositoryTechLanguage : repositoryTechLanguages) {

						MemberTechLanguage memberTechLanguage = memberTechLanguageRepository.findByMemberAndTechLanguage(repo.getMember(), repositoryTechLanguage.getTechLanguage()).orElse(null);
						if (memberTechLanguage == null) {
							memberTechLanguageRepository.save(
								MemberTechLanguage.builder()
									.member(repo.getMember())
									.techLanguage(repositoryTechLanguage.getTechLanguage())
									.build()
							);
						}
					}
				}


			}

		}
		return RepeatStatus.FINISHED;
	}
}
