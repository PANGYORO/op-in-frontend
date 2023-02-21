package dev.opin.opinbackend.batch.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.opin.opinbackend.persistence.repository.BatchTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatchTokenService {

	private final BatchTokenRepository batchTokenRepository;

	@Transactional
	public void deleteBatchToken() {
		batchTokenRepository.deleteAll();
	}
}
