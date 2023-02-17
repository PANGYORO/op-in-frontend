package com.c211.opinbackend.search.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.search.dto.response.RepositoryDto;
import com.c211.opinbackend.search.mapper.RepositoryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RepoService {
	private final RepoRepository repoRepository;

	public List<RepositoryDto> search(String query, Pageable pageable) {
		List<RepositoryDto> repositories = new ArrayList<>();

		List<Repository> findRepos = repoRepository.findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
			query, query, pageable).getContent();
		for (Repository repo : findRepos) {
			RepositoryDto repositoryDto = RepositoryMapper.toMyRepoDto(repo);
			repositories.add(repositoryDto);
		}

		return repositories;
	}
}
