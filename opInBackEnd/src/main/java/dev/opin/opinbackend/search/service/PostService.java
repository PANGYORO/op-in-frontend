package dev.opin.opinbackend.search.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.opin.opinbackend.exception.repositroy.RepositoryRuntimeException;
import dev.opin.opinbackend.persistence.entity.Repository;
import dev.opin.opinbackend.persistence.entity.RepositoryPost;
import dev.opin.opinbackend.persistence.repository.RepoPostRepository;
import dev.opin.opinbackend.persistence.repository.RepoRepository;
import dev.opin.opinbackend.search.dto.response.SearchPostDto;
import dev.opin.opinbackend.search.mapper.PostMapper;

import dev.opin.opinbackend.exception.repositroy.RepositoryExceptionEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final RepoRepository repoRepository;
	private final RepoPostRepository repoPostRepository;

	public List<SearchPostDto> search(String query, Pageable pageable) {
		List<RepositoryPost> result = repoPostRepository.findAllByTitleOrContentContaining(query, pageable)
			.getContent();

		return result.stream().map(PostMapper::toSearchPostDto).collect(Collectors.toList());
	}

	public List<SearchPostDto> searchInRepo(Long repoId, String query, Pageable pageable) {
		Repository repository = repoRepository.findById(repoId)
			.orElseThrow(() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_EXIST_EXCEPTION));

		List<RepositoryPost> result = repoPostRepository.findAllTitleOrContentContainingInRepository(repository, query,
			pageable).getContent();
		return result.stream().map(repoPost -> PostMapper.toSearchPostDto(repoPost)).collect(Collectors.toList());
	}
}
