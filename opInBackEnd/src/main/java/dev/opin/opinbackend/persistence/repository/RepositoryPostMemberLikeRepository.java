package dev.opin.opinbackend.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.opin.opinbackend.persistence.entity.RepositoryPost;
import dev.opin.opinbackend.persistence.entity.RepositoryPostMemberLike;

public interface RepositoryPostMemberLikeRepository extends JpaRepository<RepositoryPostMemberLike, Long> {

	long countByRepositoryPost(RepositoryPost repositoryPost);

	List<RepositoryPostMemberLike> findByMemberIdAndRepositoryPostId(Long memberId, Long postId);

	List<RepositoryPostMemberLike> findByMemberId(Long memberId);
}
