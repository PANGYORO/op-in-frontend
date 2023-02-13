package com.c211.opinbackend.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.persistence.entity.CommitHistory;

@Repository
public interface CommitHistoryRepository extends JpaRepository<CommitHistory, Long> {

	Optional<CommitHistory> findBySha(String sha);

	CommitHistory save(CommitHistory commitHistory);
}
