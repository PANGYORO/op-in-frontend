package com.c211.opinbackend.repo.service.repo;

import java.util.List;

import com.c211.opinbackend.repo.model.repository.RepositoryDto;

public interface RepositoryService {
	List<RepositoryDto> finRepositoryListByMember(String email);
}
