package com.c211.opinbackend.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryTechLanguage;
import com.c211.opinbackend.persistence.entity.TechLanguage;

@org.springframework.stereotype.Repository
public interface RepoTechLanguageRepository extends JpaRepository<RepositoryTechLanguage, Long> {

	List<RepositoryTechLanguage> findAll();

	List<RepositoryTechLanguage> findAllByRepository(Repository repoRepository);

	Optional<RepositoryTechLanguage> findByRepositoryAndTechLanguage(Repository repository, TechLanguage techLanguage);

	RepositoryTechLanguage save(RepositoryTechLanguage repositoryTechLanguage);
}
