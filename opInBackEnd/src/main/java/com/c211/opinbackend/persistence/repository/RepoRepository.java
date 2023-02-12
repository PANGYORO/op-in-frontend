package com.c211.opinbackend.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.Repository;
@org.springframework.stereotype.Repository
public interface RepoRepository extends JpaRepository<Repository, Long> {

	Optional<Repository> findById(String id);

	List<Repository> findAll();

	List<Repository> findByMember(Member member);

	List<Repository> findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name,
		String description);
}
