package com.c211.opinbackend.batch.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryTechLanguage;
import com.c211.opinbackend.persistence.entity.TechLanguage;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.persistence.repository.RepoTechLanguageRepository;
import com.c211.opinbackend.persistence.repository.TechLanguageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RepositoryTechLanguageService {

	final RepoRepository repoRepository;
	final RepoTechLanguageRepository repoTechLanguageRepository;
	final TechLanguageRepository techLanguageRepository;

	public void fetchTechLanguage(Repository repository, Map<String, Long> map) {
		List<RepositoryTechLanguage> lists = new LinkedList<>();

		for (String language : map.keySet()) {
			TechLanguage techLang = techLanguageRepository.findByTitle(language).orElse(null);
			if (techLang == null) {
				techLang = techLanguageRepository.save(TechLanguage.builder().title(language).build());
			}
			lists.add(
				RepositoryTechLanguage
					.builder()
					.repository(repository)
					.techLanguage(techLang)
					.count(map.get(language))
					.build()
			);
		}
		repoTechLanguageRepository.saveAll(lists);
	}
}
