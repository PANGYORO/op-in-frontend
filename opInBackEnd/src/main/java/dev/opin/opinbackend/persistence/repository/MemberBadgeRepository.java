package dev.opin.opinbackend.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.opin.opinbackend.persistence.entity.Member;
import dev.opin.opinbackend.persistence.entity.MemberBadge;

@Repository
public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Long> {

	List<MemberBadge> findByMember(Member member);
}
