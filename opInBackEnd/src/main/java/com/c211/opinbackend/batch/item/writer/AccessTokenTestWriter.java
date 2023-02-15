package com.c211.opinbackend.batch.item.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.c211.opinbackend.persistence.entity.BatchToken;
import com.c211.opinbackend.persistence.repository.BatchTokenRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccessTokenTestWriter implements ItemWriter<String> {

	private final BatchTokenRepository batchTokenRepository;

	@Override
	public void write(List<? extends String> items) throws Exception {
		for (String token : items) {
			BatchToken batchToken = BatchToken.builder()
				.accessToken(token)
				.build();

			try {
				batchTokenRepository.save(batchToken);
			} catch (Exception e) {

			}
		}
	}
}
