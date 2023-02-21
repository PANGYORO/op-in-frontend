package dev.opin.opinbackend.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.opin.opinbackend.persistence.entity.Member;
import dev.opin.opinbackend.persistence.entity.RepositoryFollow;

@Repository
public interface RepositoryFollowRepository extends JpaRepository<RepositoryFollow, Long> {

	List<RepositoryFollow> findByMember(Member member);

	List<RepositoryFollow> findByRepositoryId(Long repoId);

	List<RepositoryFollow> findByRepositoryIdAndMemberId(Long repoId, Long memberId);

	List<RepositoryFollow> findAll();
}
