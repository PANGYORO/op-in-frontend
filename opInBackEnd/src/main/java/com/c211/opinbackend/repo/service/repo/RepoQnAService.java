package com.c211.opinbackend.repo.service.repo;

import java.util.List;

import com.c211.opinbackend.repo.model.requeset.RequestQnA;
import com.c211.opinbackend.repo.model.response.QnAComment;
import com.c211.opinbackend.repo.model.response.RepoQnAResponse;

public interface RepoQnAService {
	List<RepoQnAResponse> getRepoQnALIst();

	QnAComment getQnAComment();

	Boolean createRepoQnA(RequestQnA requestQnA, String email);

}
