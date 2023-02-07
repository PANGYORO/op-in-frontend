package com.c211.opinbackend.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryTechLanguage;

public interface RepoTechLanguageRepository extends JpaRepository<RepositoryTechLanguage, Long> {
	List<RepositoryTechLanguage> findAllByRepository(Repository repoRepository);
}
