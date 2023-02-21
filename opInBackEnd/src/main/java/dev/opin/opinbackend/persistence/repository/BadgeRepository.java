package dev.opin.opinbackend.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.opin.opinbackend.persistence.entity.Badge;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

	Optional<Badge> findById(String id);
}
