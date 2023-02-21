package dev.opin.opinbackend.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.opin.opinbackend.persistence.entity.GithubContributor;
import dev.opin.opinbackend.persistence.entity.RepositoryGithubContributor;

@Repository
public interface RepositoryGithubContributorRepository extends JpaRepository<RepositoryGithubContributor, Long> {

	List<RepositoryGithubContributor> findByGithubContributor(GithubContributor contributor);

	Optional<RepositoryGithubContributor> findByGithubContributorAndRepository(GithubContributor contributor,
		dev.opin.opinbackend.persistence.entity.Repository repository);

	RepositoryGithubContributor save(RepositoryGithubContributor relation);

	@Override
	void deleteById(Long aLong);
}
