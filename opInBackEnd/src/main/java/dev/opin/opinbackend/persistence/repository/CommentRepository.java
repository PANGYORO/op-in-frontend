package dev.opin.opinbackend.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.opin.opinbackend.persistence.entity.Comment;
import dev.opin.opinbackend.persistence.entity.RepositoryPost;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByRepositoryPost(RepositoryPost repositoryPost);

	long countByRepositoryPost(RepositoryPost repositoryPost);

	List<Comment> findByRepositoryQnAId(Long id);
}
