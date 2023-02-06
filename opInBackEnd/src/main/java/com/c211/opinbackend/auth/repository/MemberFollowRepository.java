package com.c211.opinbackend.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.entity.MemberFollow;

@Repository
public interface MemberFollowRepository extends JpaRepository<MemberFollow, Long> {

	long countByFromMember(Member member);

	long countByToMember(Member member);
}
