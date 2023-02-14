package com.c211.opinbackend.persistence.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.c211.opinbackend.persistence.entity.BatchToken;

@Repository
public interface BatchTokenRepository extends JpaRepository<BatchToken, Long> {

	List<BatchToken> findAll();
	BatchToken save(BatchToken batchToken);

	Optional<BatchToken> findByAccessToken(String accessToken);

	@Transactional
	@Modifying
	@Query(value = "truncate batch_token", nativeQuery = true)
	void truncateBatchToken();


}
