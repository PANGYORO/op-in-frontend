package com.c211.opinbackend.repo.service.repo;

import java.util.List;

import com.c211.opinbackend.repo.model.dto.RepoDto;
import com.c211.opinbackend.repo.model.response.RepositoryResponseDto;

public interface RepositoryService {
	List<RepositoryResponseDto> finRepositoryListByMember(String email);

	Boolean uploadRepository(String memberEmail, RepoDto repoDto);

}
