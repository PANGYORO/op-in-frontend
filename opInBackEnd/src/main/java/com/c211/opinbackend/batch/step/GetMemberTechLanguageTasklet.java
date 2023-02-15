package com.c211.opinbackend.batch.step;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.MemberTechLanguage;
import com.c211.opinbackend.persistence.entity.RepositoryTechLanguage;
import com.c211.opinbackend.persistence.entity.TechLanguage;
import com.c211.opinbackend.persistence.repository.MemberTechLanguageRepository;
import com.c211.opinbackend.persistence.repository.RepoTechLanguageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetMemberTechLanguageTasklet implements Tasklet {

	private final RepoTechLanguageRepository repoTechLanguageRepository;
	private final MemberTechLanguageRepository memberTechLanguageRepository;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		// repository - tech language 관계 전체를 검색해와서
		List<RepositoryTechLanguage> repotechs = repoTechLanguageRepository.findAll();

		// repository.member 와 tech language 와 member tech language 로 save
		for (RepositoryTechLanguage repotech : repotechs) {
			TechLanguage tech = repotech.getTechLanguage();
			Member member = repotech.getRepository().getMember();

			MemberTechLanguage memberTechLanguage = memberTechLanguageRepository.findByMemberAndTechLanguage(member,
				tech).orElse(null);

			if (memberTechLanguage == null) {
				memberTechLanguageRepository.save(MemberTechLanguage.builder()
					.member(member)
					.techLanguage(tech)
					.build());
			}

		}

		return RepeatStatus.FINISHED;
	}
}
