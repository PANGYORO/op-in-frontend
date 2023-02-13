package com.c211.opinbackend.batch.item.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.c211.opinbackend.batch.dto.RepoTechLanguageDto;
import com.c211.opinbackend.persistence.entity.RepositoryTechLanguage;
import com.c211.opinbackend.persistence.entity.TechLanguage;
import com.c211.opinbackend.persistence.repository.RepoTechLanguageRepository;
import com.c211.opinbackend.persistence.repository.TechLanguageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetRepoTechLanguageWriter implements ItemWriter<RepoTechLanguageDto> {

	private final TechLanguageRepository techLanguageRepository;

	private final RepoTechLanguageRepository repoTechLanguageRepository;

	@Override
	public void write(List<? extends RepoTechLanguageDto> items) throws Exception {
		for (RepoTechLanguageDto repoTech : items) {
			// language 가 Tech language 테이블에 존재한다면 넘어가고, 아니면 저장한 후
			// member Tech language 테이블에 관계 저장
			try {
				TechLanguage techLanguage = techLanguageRepository.findByTitle(repoTech.getLanguage()).orElse(null);
				if (techLanguage == null) {
					techLanguage = techLanguageRepository.save(TechLanguage.builder().title(repoTech.getLanguage()).build());
				}
				repoTechLanguageRepository.save(RepositoryTechLanguage.builder().techLanguage(techLanguage).repository(repoTech.getRepository()).build());
			} catch (Exception e) {
				log.info(e.toString());
			}
		}
	}
}
