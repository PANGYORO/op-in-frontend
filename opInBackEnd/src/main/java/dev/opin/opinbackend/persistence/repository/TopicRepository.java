package dev.opin.opinbackend.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.opin.opinbackend.persistence.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

	Optional<Topic> findById(String id);

	Optional<Topic> findByTitle(String title);

	boolean existsByTitle(String title);

	Topic save(Topic topic);
}
