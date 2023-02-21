package dev.opin.opinbackend.batch.step;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import dev.opin.opinbackend.persistence.entity.MemberTechLanguage;
import dev.opin.opinbackend.persistence.entity.Repository;
import dev.opin.opinbackend.persistence.entity.RepositoryTechLanguage;
import dev.opin.opinbackend.persistence.repository.MemberTechLanguageRepository;
import dev.opin.opinbackend.persistence.repository.RepoRepository;

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
