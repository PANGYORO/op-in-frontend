package com.c211.opinbackend.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.entity.RepositoryContributor;

@Repository
public interface RepoContributorRepository extends JpaRepository<RepositoryContributor, Long> {

	List<RepositoryContributor> findByMember(Member member);
}
