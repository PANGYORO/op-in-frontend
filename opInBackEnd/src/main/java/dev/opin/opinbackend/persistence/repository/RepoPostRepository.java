package dev.opin.opinbackend.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.opin.opinbackend.persistence.entity.Member;
import dev.opin.opinbackend.persistence.entity.Repository;
import dev.opin.opinbackend.persistence.entity.RepositoryPost;

public interface RepoPostRepository extends JpaRepository<RepositoryPost, Long> {

	List<RepositoryPost> findByMember(Member member);

	List<RepositoryPost> findByRepositoryId(Long repoId);

	List<RepositoryPost> findByMember_Nickname(String memberId);

	@Query("SELECT post " + "FROM RepositoryPost post " + "WHERE post.titleContent.content LIKE CONCAT('%',:query, '%')"
		+ "OR post.titleContent.title LIKE CONCAT('%', :query, '%')")
	Page<RepositoryPost> findAllByTitleOrContentContaining(String query, Pageable pageable);

	@Query(
		"SELECT post " + "FROM RepositoryPost post " + "WHERE (post.titleContent.content LIKE CONCAT('%',:query, '%')"
			+ "OR post.titleContent.title LIKE CONCAT('%', :query, '%'))" + "AND post.repository = :repository")
	Page<RepositoryPost> findAllTitleOrContentContainingInRepository(Repository repository, String query,
		Pageable pageable);

	@Query("SELECT DISTINCT post FROM RepositoryPost post JOIN post.member order by  post.date desc")
	Page<RepositoryPost> findDistinctByRepositoryCreatedAt(Pageable pageable);

	@Query("SELECT DISTINCT post FROM RepositoryPost post JOIN post.member order by post.likeList.size desc")
	Page<RepositoryPost> findDistinctByRepositoryLike(Pageable pageable);

}
