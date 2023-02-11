package com.c211.opinbackend.repo.service.repo;

import java.util.List;

import com.c211.opinbackend.repo.model.requeset.RequestQnA;
import com.c211.opinbackend.repo.model.requeset.RequestUpdateQnA;
import com.c211.opinbackend.repo.model.response.RepoQnAResponse;

public interface RepoQnAService {
	List<RepoQnAResponse> getRepoQnALIst(Long repoId);

	Long createRepoQnA(RequestQnA requestQnA, String email);

	Boolean deleteRepoQnA(Long qnaId);

	Boolean updateRepoQnA(RequestUpdateQnA requestUpdateQnA);

}
