package com.c211.opinbackend.repo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.repo.entitiy.RepositoryPost;

public interface RepoPostRepository extends JpaRepository<RepositoryPost, Long> {

	Optional<RepositoryPost> findById(String id);

	List<RepositoryPost> findByMember(Member member);


}
