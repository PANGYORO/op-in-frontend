package com.c211.opinbackend.repo.service.repoTechlag;

import java.util.List;

import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryTechLanguage;

public interface RepoTechLanguageService {

	List<RepositoryTechLanguage> getRepoTechLangByRepos(Repository repository);
}
