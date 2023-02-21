package dev.opin.opinbackend.batch.service;

import org.springframework.stereotype.Service;

import dev.opin.opinbackend.persistence.entity.PullRequest;
import dev.opin.opinbackend.persistence.entity.Repository;
import dev.opin.opinbackend.persistence.repository.PullRequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RepositoryPullRequestService {

	final PullRequestRepository pullRequestRepository;

	public void save(Repository repository, PullRequest[] requests) {
		for (PullRequest request : requests) {
			request.setRepository(repository);

			pullRequestRepository.save(request);
		}
	}
}
