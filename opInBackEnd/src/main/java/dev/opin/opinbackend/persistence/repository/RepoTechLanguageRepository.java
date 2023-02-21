package dev.opin.opinbackend.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.opin.opinbackend.persistence.entity.Repository;
import dev.opin.opinbackend.persistence.entity.RepositoryTechLanguage;
import dev.opin.opinbackend.persistence.entity.TechLanguage;

@org.springframework.stereotype.Repository
public interface RepoTechLanguageRepository extends JpaRepository<RepositoryTechLanguage, Long> {

	List<RepositoryTechLanguage> findAll();

	List<RepositoryTechLanguage> findAllByRepository(Repository repoRepository);

	Optional<RepositoryTechLanguage> findByRepositoryAndTechLanguage(Repository repository, TechLanguage techLanguage);

	RepositoryTechLanguage save(RepositoryTechLanguage repositoryTechLanguage);
}
