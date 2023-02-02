package com.c211.opinbackend.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.entity.MemberFollow;

public interface MemberFollowRepository extends JpaRepository<MemberFollow, Long> {

	long countByFromMember(Member member);

	long countByToMember(Member member);
}
