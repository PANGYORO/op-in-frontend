package dev.opin.opinbackend.search.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.opin.opinbackend.persistence.entity.Repository;
import dev.opin.opinbackend.persistence.repository.RepoRepository;
import dev.opin.opinbackend.search.dto.response.RepositoryDto;
import dev.opin.opinbackend.search.mapper.RepositoryMapper;

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
