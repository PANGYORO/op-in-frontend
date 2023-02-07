package com.c211.opinbackend.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.RepositoryFollow;

@Repository
public interface RepositoryFollowRepository extends JpaRepository<RepositoryFollow, Long> {

	List<RepositoryFollow> findByMember(Member member);
}
