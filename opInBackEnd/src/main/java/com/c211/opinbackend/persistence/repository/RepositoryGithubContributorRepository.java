package com.c211.opinbackend.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.persistence.entity.GithubContributor;
import com.c211.opinbackend.persistence.entity.RepositoryGithubContributor;

@Repository
public interface RepositoryGithubContributorRepository extends JpaRepository<RepositoryGithubContributor, Long> {

	Optional<RepositoryGithubContributor> findByGithubContributor(GithubContributor contributor);
	RepositoryGithubContributor save(RepositoryGithubContributor relation);
}
