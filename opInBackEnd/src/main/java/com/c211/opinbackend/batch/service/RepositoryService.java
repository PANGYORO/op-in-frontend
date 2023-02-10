package com.c211.opinbackend.batch.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.c211.opinbackend.batch.dto.github.RepositoryDto;
import com.c211.opinbackend.batch.dto.mapper.RepoMapper;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.PullRequest;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.persistence.repository.PullRequestRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RepositoryService {
	private final RepoRepository repoRepository;
	private final MemberRepository memberRepository;
	private final PullRequestRepository pullRequestRepository;
	private final RepoMapper repoMapper;

	public Repository findById(Long id) {
		return repoRepository.findById(id).orElse(null);
	}
	public Repository saveOrUpdateRepository(RepositoryDto repositoryDto) {
		Member member = memberRepository.findByGithubId(repositoryDto.getOwner().getId().toString()).orElse(null);
		Repository repo = repoMapper.toRepository(repositoryDto, member);

		return repoRepository.save(repo);
	}


}
