package com.c211.opinbackend.batch.item.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.c211.opinbackend.persistence.entity.PullRequest;
import com.c211.opinbackend.persistence.repository.PullRequestRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetRepoPullRequestWriter implements ItemWriter<PullRequest> {

	private final PullRequestRepository pullRequestRepository;

	@Override
	public void write(List<? extends PullRequest> items) throws Exception {
		for (PullRequest pullReque : items) {
			try {
				pullRequestRepository.save(pullReque);
			} catch (Exception e) {
				log.info(e.toString());
			}
		}
	}
}
