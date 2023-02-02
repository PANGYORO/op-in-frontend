package com.c211.opinbackend.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.entity.MemberBadge;

public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Long> {

	List<MemberBadge> findByMember(Member member);
}
