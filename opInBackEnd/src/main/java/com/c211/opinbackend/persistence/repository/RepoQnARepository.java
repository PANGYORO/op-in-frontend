package com.c211.opinbackend.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c211.opinbackend.persistence.entity.RepositoryQnA;

public interface RepoQnARepository extends JpaRepository<RepositoryQnA, Long> {
	List<RepositoryQnA> findByRepositoryId(Long id);

}
