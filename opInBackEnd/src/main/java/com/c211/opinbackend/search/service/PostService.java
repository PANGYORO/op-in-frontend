package com.c211.opinbackend.search.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.c211.opinbackend.persistence.entity.RepositoryPost;
import com.c211.opinbackend.persistence.repository.RepoPostRepository;
import com.c211.opinbackend.search.dto.response.SearchPostDto;
import com.c211.opinbackend.search.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final RepoPostRepository repoPostRepository;
	public List<SearchPostDto> search(String query, Pageable pageable) {
		List<RepositoryPost> result = repoPostRepository.findAllByTitleOrContentCOntaining(query, pageable).getContent();

		return result.stream()
			.map(repoPost -> PostMapper.toSearchPostDto(repoPost))
			.collect(Collectors.toList());
	}
}
