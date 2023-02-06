package com.c211.opinbackend.repo.service.repo;

import java.util.List;

import com.c211.opinbackend.repo.model.repository.RepositoryDto;
import com.c211.opinbackend.repo.model.requeset.CreatePostRequest;

public interface RepositoryService {
	List<RepositoryDto> finRepositoryListByMember(String email);

	Boolean createPost(CreatePostRequest createPostRequest, String email);
}
