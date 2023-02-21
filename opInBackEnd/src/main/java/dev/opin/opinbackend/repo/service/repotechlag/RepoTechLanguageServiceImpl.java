package dev.opin.opinbackend.repo.service.repotechlag;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.opin.opinbackend.exception.repotechlan.RepoTechLangExceptionEnum;
import dev.opin.opinbackend.exception.repotechlan.RepoTechLangRuntimeException;
import dev.opin.opinbackend.persistence.entity.Repository;
import dev.opin.opinbackend.persistence.entity.RepositoryTechLanguage;
import dev.opin.opinbackend.persistence.repository.RepoTechLanguageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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
