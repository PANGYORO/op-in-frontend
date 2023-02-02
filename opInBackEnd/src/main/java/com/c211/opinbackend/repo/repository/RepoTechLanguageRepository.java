package com.c211.opinbackend.repo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c211.opinbackend.repo.entitiy.Repository;
import com.c211.opinbackend.repo.entitiy.RepositoryTechLanguage;

public interface RepoTechLanguageRepository extends JpaRepository<RepositoryTechLanguage, Long> {
	List<RepositoryTechLanguage> findAllByRepository(Repository repoRepository);
}
