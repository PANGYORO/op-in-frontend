package dev.opin.opinbackend.batch.service;

import org.springframework.stereotype.Service;

import dev.opin.opinbackend.batch.dto.github.RepositoryDto;
import dev.opin.opinbackend.batch.dto.mapper.RepoMapper;
import dev.opin.opinbackend.persistence.entity.Member;
import dev.opin.opinbackend.persistence.entity.Repository;
import dev.opin.opinbackend.persistence.repository.MemberRepository;
import dev.opin.opinbackend.persistence.repository.RepoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RepositoryService {
	private final RepoRepository repoRepository;
	private final MemberRepository memberRepository;

	public Repository findById(Long id) {
		return repoRepository.findById(id).orElse(null);
	}

	public Repository saveOrUpdateRepository(RepositoryDto repositoryDto) {
		Member member = memberRepository.findByGithubId(repositoryDto.getOwner().getId().toString()).orElse(null);
		Repository repo = RepoMapper.toRepository(repositoryDto, member);
		return repoRepository.save(repo);
	}

}
