package com.c211.opinbackend.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c211.opinbackend.persistence.entity.RepositoryPost;
import com.c211.opinbackend.persistence.entity.RepositoryPostMemberLike;

public interface RepositoryPostMemberLikeRepository extends JpaRepository<RepositoryPostMemberLike, Long> {

	long countByRepositoryPost(RepositoryPost repositoryPost);

	List<RepositoryPostMemberLike> findByMemberIdAndRepositoryPostId(Long memberId, Long PostId);
}
