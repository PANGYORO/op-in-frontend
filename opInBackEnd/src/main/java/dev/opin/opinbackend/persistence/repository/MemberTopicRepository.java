package dev.opin.opinbackend.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.opin.opinbackend.persistence.entity.Member;
import dev.opin.opinbackend.persistence.entity.MemberTopic;
import dev.opin.opinbackend.persistence.entity.Topic;

@Repository
public interface MemberTopicRepository extends JpaRepository<MemberTopic, Long> {

	List<MemberTopic> findByMember(Member member);

	Optional<MemberTopic> findByMemberAndTopic(Member member, Topic topic);

	MemberTopic save(MemberTopic memberTopic);

	@Override
	void delete(MemberTopic entity);
}
