package com.c211.opinbackend.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.auth.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByEmailAndPassword(String email, String password);

	Optional<Member> findByEmail(String email);
	Optional<Member> findByGithubId(String githubId);

	boolean existsByEmail(String email);

	boolean existsByNickname(String nickname);
	boolean existsByGithubId(String githubId);
	boolean existsByEmailAndAndGithubSyncFl(String email, boolean sync);
	Member save(Member member);
}
