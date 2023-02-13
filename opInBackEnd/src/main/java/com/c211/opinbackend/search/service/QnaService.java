package com.c211.opinbackend.search.service;

import static com.c211.opinbackend.exception.repositroy.RepositoryExceptionEnum.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.c211.opinbackend.exception.repositroy.RepositoryRuntimeException;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryQnA;
import com.c211.opinbackend.persistence.repository.RepoQnARepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.search.dto.response.SearchQnaDto;
import com.c211.opinbackend.search.mapper.QnaMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaService {
	private final RepoRepository repoRepository;
	private final RepoQnARepository repoQnARepository;

	public List<SearchQnaDto> searchInRepo(Long repoId, String query, Pageable pageable) {
		Repository repository = repoRepository.findById(repoId)
			.orElseThrow(() -> new RepositoryRuntimeException(REPOSITORY_EXIST_EXCEPTION));

		List<RepositoryQnA> result = repoQnARepository.findAllByRepositoryAndContentContaining(repository, query,
			pageable).getContent();
		return result.stream().map(repoQna -> QnaMapper.toSearchQnaDto(repoQna)).collect(Collectors.toList());
	}
}
