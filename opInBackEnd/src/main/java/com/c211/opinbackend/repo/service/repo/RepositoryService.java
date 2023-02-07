package com.c211.opinbackend.repo.service.repo;

import java.util.List;

import com.c211.opinbackend.repo.model.repository.RepoDto;
import com.c211.opinbackend.repo.model.requeset.CreatePostRequest;
import com.c211.opinbackend.repo.model.response.RepositoryResponseDto;

public interface RepositoryService {
	List<RepositoryResponseDto> finRepositoryListByMember(String email);

	Boolean createPostToRepository(Long repositoryId, CreatePostRequest createPostRequest, String email);

	/**
	 * 래포지토리 등록
	 * 래포지토리에 맴버 등록 가능하나 맴버는 널일수도 있다.
	 */
	Boolean uploadRepository(String memberEmail, RepoDto repoDto);
}
