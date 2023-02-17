package com.c211.opinbackend.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.MemberTechLanguage;
import com.c211.opinbackend.persistence.entity.MemberTopic;
import com.c211.opinbackend.persistence.entity.TechLanguage;

@Repository
public interface MemberTechLanguageRepository extends JpaRepository<MemberTechLanguage, Long> {

	List<MemberTechLanguage> findByMember(Member member);

	Optional<MemberTechLanguage> findByMemberAndTechLanguage(Member member, TechLanguage techLanguage);

	MemberTechLanguage save(MemberTechLanguage memberTechLanguage);

	@Override
	void delete(MemberTechLanguage entity);

}
