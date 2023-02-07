package com.c211.opinbackend.repo.service.repo;

import com.c211.opinbackend.repo.model.dto.RepoDto;
import com.c211.opinbackend.repo.model.requeset.CreatePostRequest;

public interface RepositoryPostService {
	Boolean createPostToRepository(CreatePostRequest createPostRequest, String memberEmail);

	/**
	 * 래포지토리 등록
	 * 래포지토리에 맴버 등록 가능하나 맴버는 널일수도 있다.
	 */
	Boolean uploadRepository(String memberEmail, RepoDto repoDto);
}
