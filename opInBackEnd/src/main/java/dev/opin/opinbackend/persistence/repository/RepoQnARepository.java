package dev.opin.opinbackend.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.opin.opinbackend.persistence.entity.Repository;
import dev.opin.opinbackend.persistence.entity.RepositoryQnA;

public interface RepoQnARepository extends JpaRepository<RepositoryQnA, Long> {
	List<RepositoryQnA> findByRepositoryId(Long id);

	Page<RepositoryQnA> findAllByRepositoryAndContentContaining(Repository repository, String query, Pageable pageable);
}
