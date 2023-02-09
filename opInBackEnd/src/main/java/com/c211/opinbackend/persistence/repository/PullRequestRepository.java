package com.c211.opinbackend.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c211.opinbackend.persistence.entity.PullRequest;

public interface PullRequestRepository extends JpaRepository<PullRequest, Long> {
}
