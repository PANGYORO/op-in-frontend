package dev.opin.opinbackend.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.opin.opinbackend.persistence.entity.GithubContributor;

@Repository
public interface GithubContributorRepository extends JpaRepository<GithubContributor, Long> {

	Optional<GithubContributor> findByAuthorId(String authorId);

	@Override
	void deleteById(Long aLong);

	GithubContributor save(GithubContributor contributor);
}
