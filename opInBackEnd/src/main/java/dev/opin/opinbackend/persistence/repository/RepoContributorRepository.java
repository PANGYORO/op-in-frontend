package dev.opin.opinbackend.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.opin.opinbackend.persistence.entity.Member;
import dev.opin.opinbackend.persistence.entity.RepositoryContributor;

@Repository
public interface RepoContributorRepository extends JpaRepository<RepositoryContributor, Long> {

	List<RepositoryContributor> findByMember(Member member);
	Optional<RepositoryContributor> findByMemberAndRepository(Member member, dev.opin.opinbackend.persistence.entity.Repository repository);

	RepositoryContributor save(RepositoryContributor contributor);
}
