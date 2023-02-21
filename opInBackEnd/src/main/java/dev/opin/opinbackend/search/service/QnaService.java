package dev.opin.opinbackend.search.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.opin.opinbackend.exception.repositroy.RepositoryRuntimeException;
import dev.opin.opinbackend.persistence.entity.Repository;
import dev.opin.opinbackend.persistence.entity.RepositoryQnA;
import dev.opin.opinbackend.persistence.repository.RepoQnARepository;
import dev.opin.opinbackend.persistence.repository.RepoRepository;
import dev.opin.opinbackend.search.dto.response.SearchQnaDto;
import dev.opin.opinbackend.search.mapper.QnaMapper;

import dev.opin.opinbackend.exception.repositroy.RepositoryExceptionEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaService {
	private final RepoRepository repoRepository;
	private final RepoQnARepository repoQnARepository;

	public List<SearchQnaDto> searchInRepo(Long repoId, String query, Pageable pageable) {
		Repository repository = repoRepository.findById(repoId)
			.orElseThrow(() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_EXIST_EXCEPTION));

		List<RepositoryQnA> result = repoQnARepository.findAllByRepositoryAndContentContaining(repository, query,
			pageable).getContent();
		return result.stream().map(repoQna -> QnaMapper.toSearchQnaDto(repoQna)).collect(Collectors.toList());
	}
}
