package dev.opin.opinbackend.repo.service.repo;

import java.util.List;

import dev.opin.opinbackend.repo.model.requeset.RequestQnA;
import dev.opin.opinbackend.repo.model.requeset.RequestUpdateQnA;
import dev.opin.opinbackend.repo.model.response.RepoQnAResponse;

public interface RepoQnAService {
	List<RepoQnAResponse> getRepoQnAList(Long repoId);

	RepoQnAResponse createRepoQnA(RequestQnA requestQnA, String email);

	Boolean deleteRepoQnA(Long qnaId);

	Boolean updateRepoQnA(RequestUpdateQnA requestUpdateQnA);

}
