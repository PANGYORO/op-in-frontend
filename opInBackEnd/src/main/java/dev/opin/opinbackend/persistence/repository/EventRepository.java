package dev.opin.opinbackend.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.opin.opinbackend.persistence.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
