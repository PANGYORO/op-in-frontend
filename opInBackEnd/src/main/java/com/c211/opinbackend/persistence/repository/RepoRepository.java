package com.c211.opinbackend.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.Repository;

@org.springframework.stereotype.Repository
public interface RepoRepository extends JpaRepository<Repository, Long> {

	Optional<Repository> findById(String id);

	List<Repository> findAll();

	List<Repository> findByMember(Member member);

	@Query("SELECT repo FROM Repository repo JOIN FETCH repo.member WHERE repo.member.id = :memberId")
	List<Repository> findAllByMemberId(Long memberId);

	List<Repository> findByMemberEmail(String email);

	List<Repository> findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name,
		String description);

	Page<Repository> findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description,
		Pageable pageable);

	List<Repository> findTop10ByOrderByStargazersCountDesc();
}
