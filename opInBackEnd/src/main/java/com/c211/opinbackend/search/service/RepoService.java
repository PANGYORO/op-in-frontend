package com.c211.opinbackend.search.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.search.dto.RepositoryResponseDto;
import com.c211.opinbackend.search.mapper.RepositoryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RepoService {
	final RepoRepository repoRepository;

	public List<RepositoryResponseDto> search(String query) {
		List<RepositoryResponseDto> repositories = new ArrayList<>();

		List<Repository> findRepos = repoRepository.findAllByNameContainingOrDescriptionContaining(query, query);
		for (Repository repo : findRepos) {
			RepositoryResponseDto repositoryResponseDto = RepositoryMapper.toMyRepoDto(repo);
			repositories.add(repositoryResponseDto);
		}

		return repositories;
	}
}
