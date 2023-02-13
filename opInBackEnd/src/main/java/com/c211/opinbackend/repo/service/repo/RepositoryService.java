package com.c211.opinbackend.repo.service.repo;

import java.util.List;

import com.c211.opinbackend.repo.model.dto.RepoDto;
import com.c211.opinbackend.repo.model.response.RepoDetailResponse;
import com.c211.opinbackend.repo.model.response.RepositoryResponseDto;
import com.c211.opinbackend.repo.model.response.RepositoryResponseSimpleDto;

public interface RepositoryService {
	List<RepositoryResponseDto> finRepositoryListByMember(String email);

	List<RepositoryResponseSimpleDto> findRepositorySimpleList(Long memberId);

	Boolean uploadRepository(String memberEmail, RepoDto repoDto);

	RepoDetailResponse getDetailResponse(Long repoId);
}
