package com.c211.opinbackend.batch.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.c211.opinbackend.batch.dto.github.PullRequestDto;
import com.c211.opinbackend.persistence.entity.PullRequest;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.repository.PullRequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RepositoryPullRequestService {

	final PullRequestRepository pullRequestRepository;

	public void save(Repository repository, PullRequest[] requests) {
		for(PullRequest request : requests) {
			request.setRepository(repository);

			pullRequestRepository.save(request);
		}
	}
}
