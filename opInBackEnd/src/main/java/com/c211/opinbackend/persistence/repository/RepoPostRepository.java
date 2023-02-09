package com.c211.opinbackend.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.RepositoryPost;

public interface RepoPostRepository extends JpaRepository<RepositoryPost, Long> {

	List<RepositoryPost> findByMember(Member member);

	List<RepositoryPost> findByRepositoryId(Long repoId);

	List<RepositoryPost> findByMember_Nickname(String memberId);

	@Query("SELECT post "
		+ "FROM RepositoryPost post "
		+ "WHERE post.titleContent.content LIKE CONCAT('%',:query, '%')"
		+ "OR post.titleContent.title LIKE CONCAT('%', :query, '%')")
	List<RepositoryPost> findAllByTitleOrContentCOntaining(String query);

}
