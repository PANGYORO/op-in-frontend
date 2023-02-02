package com.c211.opinbackend.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.auth.entity.Badge;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

	Optional<Badge> findById(String id);
}
