package com.c211.opinbackend.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c211.opinbackend.persistence.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
