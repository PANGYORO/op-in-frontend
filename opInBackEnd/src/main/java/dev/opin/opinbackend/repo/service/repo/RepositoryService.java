package dev.opin.opinbackend.repo.service.repo;

import java.util.List;

import dev.opin.opinbackend.repo.model.dto.RepoDto;
import dev.opin.opinbackend.repo.model.response.RepoDetailResponse;
import dev.opin.opinbackend.repo.model.response.RepositoryResponseDto;
import dev.opin.opinbackend.repo.model.response.RepositoryResponseSimpleDto;

public interface RepositoryService {
	List<RepositoryResponseDto> finRepositoryListByMember(String email);

	List<RepositoryResponseSimpleDto> findRepositorySimpleList(Long memberId);

	Boolean uploadRepository(String memberEmail, RepoDto repoDto);

	RepoDetailResponse getDetailResponse(Long repoId);

	boolean addEnter(String title);
}
