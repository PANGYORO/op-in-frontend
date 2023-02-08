package com.c211.opinbackend.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.persistence.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

	Optional<Topic> findById(String id);
}
