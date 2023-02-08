package com.c211.opinbackend.repo.service.repoTechlag;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.c211.opinbackend.exception.repotechlan.RepoTechLangExceptionEnum;
import com.c211.opinbackend.exception.repotechlan.RepoTechLangRuntimeException;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryTechLanguage;
import com.c211.opinbackend.persistence.repository.RepoTechLanguageRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RepoTechLanguageServiceImpl implements RepoTechLanguageService {
	private final RepoTechLanguageRepository repoTechLanguageRepository;

	@Override
	@Transactional
	public List<RepositoryTechLanguage> getRepoTechLangByRepos(Repository repository) {
		List<RepositoryTechLanguage> allByRepository = repoTechLanguageRepository.findAllByRepository(repository);
		if (allByRepository.size() == 0) {
			throw new RepoTechLangRuntimeException(RepoTechLangExceptionEnum.REPO_TECH_LANG_EXCEPTION);
		}
		return allByRepository;
	}
}
