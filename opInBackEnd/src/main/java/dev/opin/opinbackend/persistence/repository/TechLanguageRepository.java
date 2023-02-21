package dev.opin.opinbackend.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.opin.opinbackend.persistence.entity.TechLanguage;

@Repository
public interface TechLanguageRepository extends JpaRepository<TechLanguage, Long> {

	Optional<TechLanguage> findById(String id);

	Optional<TechLanguage> findByTitle(String title);

	TechLanguage save(TechLanguage techLanguage);

	List<TechLanguage> findAll();
}
