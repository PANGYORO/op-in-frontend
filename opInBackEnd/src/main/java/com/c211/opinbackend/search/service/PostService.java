package com.c211.opinbackend.search.service;

import static com.c211.opinbackend.exception.repositroy.RepositoryExceptionEnum.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.c211.opinbackend.exception.repositroy.RepositoryRuntimeException;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryPost;
import com.c211.opinbackend.persistence.repository.RepoPostRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.search.dto.response.SearchPostDto;
import com.c211.opinbackend.search.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final RepoRepository repoRepository;
	private final RepoPostRepository repoPostRepository;
	public List<SearchPostDto> search(String query, Pageable pageable) {
		List<RepositoryPost> result = repoPostRepository.findAllByTitleOrContentContaining(query, pageable).getContent();

		return result.stream()
			.map(repoPost -> PostMapper.toSearchPostDto(repoPost))
			.collect(Collectors.toList());
	}

	public List<SearchPostDto> searchInRepo(Long repoId, String query, Pageable pageable) {
		Repository repository = repoRepository
			.findById(repoId)
			.orElseThrow(() -> new RepositoryRuntimeException(REPOSITORY_EXIST_EXCEPTION));

		List<RepositoryPost> result = repoPostRepository.findAllTitleOrContentContainingInRepository(repository, query, pageable).getContent();
		return result.stream()
			.map(repoPost -> PostMapper.toSearchPostDto(repoPost))
			.collect(Collectors.toList());
	}
}
