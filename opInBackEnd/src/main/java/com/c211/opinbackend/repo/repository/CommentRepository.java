package com.c211.opinbackend.repo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c211.opinbackend.repo.entitiy.Comment;
import com.c211.opinbackend.repo.entitiy.RepositoryPost;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByRepositoryPost(RepositoryPost repositoryPost);

	long countByRepositoryPost(RepositoryPost repositoryPost);
}
