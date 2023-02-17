package com.c211.opinbackend.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.MemberFollow;
import com.c211.opinbackend.persistence.entity.MemberTopic;
import com.c211.opinbackend.persistence.entity.Topic;

@Repository
public interface MemberTopicRepository extends JpaRepository<MemberTopic, Long> {

	List<MemberTopic> findByMember(Member member);

	Optional<MemberTopic> findByMemberAndTopic(Member member, Topic topic);

	MemberTopic save(MemberTopic memberTopic);

	@Override
	void delete(MemberTopic entity);
}
