package com.c211.opinbackend.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.auth.entity.TechLanguage;

@Repository
public interface TechLanguageRepository extends JpaRepository<TechLanguage, Long> {

	Optional<TechLanguage> findById(String id);
}
