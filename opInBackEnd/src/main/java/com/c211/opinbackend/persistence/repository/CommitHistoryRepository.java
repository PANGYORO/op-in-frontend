package com.c211.opinbackend.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.persistence.entity.CommitHistory;

@Repository
public interface CommitHistoryRepository extends JpaRepository<CommitHistory, Long> {

	CommitHistory save(CommitHistory commitHistory);
}
