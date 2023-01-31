package com.c211.opinbackend.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c211.opinbackend.auth.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByEmailAndPassword(String email, String password);
	Optional<Member> findByEmail(String email);

	// Member findByEmail(String email);
	boolean existsByEmail(String email);
	Member save(Member member);
}
