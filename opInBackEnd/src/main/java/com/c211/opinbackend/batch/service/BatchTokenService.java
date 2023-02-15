package com.c211.opinbackend.batch.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.c211.opinbackend.persistence.repository.BatchTokenRepository;

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
