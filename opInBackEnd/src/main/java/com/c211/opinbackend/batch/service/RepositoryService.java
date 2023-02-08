package com.c211.opinbackend.batch.service;

import org.springframework.stereotype.Service;

import com.c211.opinbackend.batch.dto.github.RepositoryDto;
import com.c211.opinbackend.batch.dto.mapper.RepoMapper;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RepositoryService {
	private final RepoRepository repoRepository;
	private final MemberRepository memberRepository;
	private final RepoMapper repoMapper;

	public Repository saveOrUpdateRepository(RepositoryDto repositoryDto) {
		Member member = memberRepository.findByGithubId(repositoryDto.getOwner().getId().toString()).orElseGet(null);
		Repository repo = repoMapper.toRepository(repositoryDto, member);

		return repoRepository.save(repo);
	}

}
