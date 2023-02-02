package com.c211.opinbackend.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c211.opinbackend.auth.entity.Badge;

public interface BadgeRepository extends JpaRepository<Badge, Long> {

	Optional<Badge> findById(String id);
}
