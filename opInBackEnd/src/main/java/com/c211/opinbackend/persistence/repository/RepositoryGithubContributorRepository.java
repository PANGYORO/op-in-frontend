package com.c211.opinbackend.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.persistence.entity.GithubContributor;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.RepositoryGithubContributor;

@Repository
public interface RepositoryGithubContributorRepository extends JpaRepository<RepositoryGithubContributor, Long> {

	List<RepositoryGithubContributor> findByGithubContributor(GithubContributor contributor);

	Optional<RepositoryGithubContributor> findByGithubContributorAndRepository(GithubContributor contributor,
		com.c211.opinbackend.persistence.entity.Repository repository);

	RepositoryGithubContributor save(RepositoryGithubContributor relation);

	@Override
	void deleteById(Long aLong);
}
