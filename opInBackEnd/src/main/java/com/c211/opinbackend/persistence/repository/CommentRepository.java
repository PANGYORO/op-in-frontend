package com.c211.opinbackend.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c211.opinbackend.persistence.entity.Comment;
import com.c211.opinbackend.persistence.entity.RepositoryPost;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByRepositoryPost(RepositoryPost repositoryPost);

	long countByRepositoryPost(RepositoryPost repositoryPost);

	List<Comment> findByRepositoryQnAId(Long id);
}
