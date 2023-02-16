package com.c211.opinbackend.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.persistence.entity.GithubContributor;

@Repository
public interface GithubContributorRepository extends JpaRepository<GithubContributor, Long> {

	Optional<GithubContributor> findByAuthorId(String authorId);
	GithubContributor save(GithubContributor contributor);
}
