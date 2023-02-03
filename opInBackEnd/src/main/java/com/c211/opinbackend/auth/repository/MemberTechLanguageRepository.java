package com.c211.opinbackend.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.entity.MemberTechLanguage;

@Repository
public interface MemberTechLanguageRepository extends JpaRepository<MemberTechLanguage, Long> {

	List<MemberTechLanguage> findByMember(Member member);

}
