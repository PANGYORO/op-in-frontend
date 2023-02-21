package dev.opin.opinbackend.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.opin.opinbackend.persistence.entity.Enterprise;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

	Enterprise save(Enterprise enterprise);

	List<Enterprise> findAll();
}
