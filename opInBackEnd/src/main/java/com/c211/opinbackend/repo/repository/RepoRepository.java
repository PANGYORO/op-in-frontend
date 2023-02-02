package com.c211.opinbackend.repo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.repo.entitiy.Repository;

public interface RepoRepository extends JpaRepository<Repository, Long> {

	Optional<Repository> findById(String id);

	List<Repository> findByMember(Member member);

}
