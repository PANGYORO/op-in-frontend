package dev.opin.opinbackend.repo.service.repotechlag;

import java.util.List;

import dev.opin.opinbackend.persistence.entity.Repository;
import dev.opin.opinbackend.persistence.entity.RepositoryTechLanguage;

public interface RepoTechLanguageService {

	List<RepositoryTechLanguage> getRepoTechLangByRepos(Repository repository);
}
