package com.c211.opinbackend.repo.service.repo;

import java.util.List;

import com.c211.opinbackend.repo.model.dto.RepoDto;
import com.c211.opinbackend.repo.model.requeset.CreatePostRequest;
import com.c211.opinbackend.repo.model.response.RepoPostDetailResponse;
import com.c211.opinbackend.repo.model.response.RepoPostSimpleResponse;

public interface RepositoryPostService {
	Boolean createPostToRepository(CreatePostRequest createPostRequest, String memberEmail);
	// TODO: 2023/02/07 서비스 명 작성중

	/**
	 * 임시 래포지토리 등록
	 * 래포지토리에 맴버 등록 가능하나 맴버는 널일수도 있다.
	 */
	Boolean uploadRepository(String memberEmail, RepoDto repoDto);

	List<RepoPostSimpleResponse> getAllPostList();

	RepoPostDetailResponse getRepoDetail(Long postId);

	Boolean deleteRepo(Long postId);

}
