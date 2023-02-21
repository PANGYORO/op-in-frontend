package dev.opin.opinbackend.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.opin.opinbackend.persistence.entity.PullRequest;

public interface PullRequestRepository extends JpaRepository<PullRequest, Long> {
}
