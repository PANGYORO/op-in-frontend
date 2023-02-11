package com.c211.opinbackend.persistence.repository;

import org.springframework.stereotype.Repository;

import com.c211.opinbackend.persistence.entity.CommitHistory;

@Repository
public interface CommitHistoryRepository {

	CommitHistory save(CommitHistory commitHistory);
}
