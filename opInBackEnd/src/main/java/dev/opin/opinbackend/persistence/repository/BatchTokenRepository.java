package dev.opin.opinbackend.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.opin.opinbackend.persistence.entity.BatchToken;

@Repository
public interface BatchTokenRepository extends JpaRepository<BatchToken, Long> {

	List<BatchToken> findAll();

	BatchToken save(BatchToken batchToken);

	Optional<BatchToken> findByAccessToken(String accessToken);

	@Override
	void deleteAll();
}
