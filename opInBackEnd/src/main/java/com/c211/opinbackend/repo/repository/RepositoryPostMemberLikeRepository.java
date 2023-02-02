package com.c211.opinbackend.repo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c211.opinbackend.repo.entitiy.RepositoryPost;
import com.c211.opinbackend.repo.entitiy.RepositoryPostMemberLike;

public interface RepositoryPostMemberLikeRepository extends JpaRepository<RepositoryPostMemberLike, Long> {

	long countByRepositoryPost(RepositoryPost repositoryPost);
}
