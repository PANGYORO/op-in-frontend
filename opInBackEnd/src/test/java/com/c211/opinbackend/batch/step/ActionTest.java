package com.c211.opinbackend.batch.step;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.c211.opinbackend.batch.dto.github.PullRequestDto;
import com.c211.opinbackend.batch.dto.github.RepositoryDto;
import com.c211.opinbackend.batch.dto.mapper.PullRequestMapper;
import com.c211.opinbackend.batch.service.RepositoryPullRequestService;
import com.c211.opinbackend.batch.service.RepositoryService;
import com.c211.opinbackend.batch.service.RepositoryTechLanguageService;
import com.c211.opinbackend.persistence.entity.PullRequest;
import com.c211.opinbackend.persistence.entity.Repository;

@SpringBootTest

public class ActionTest {
	Action action;
	RepositoryService repositoryService;
	RepositoryTechLanguageService repositoryTechLanguageService;
	RepositoryPullRequestService repositoryPullRequestService;
	@Autowired
	public ActionTest(
		Action action,
		RepositoryService repositoryService,
		RepositoryTechLanguageService repositoryTechLanguageService,
		RepositoryPullRequestService repositoryPullRequestService
	) {
		this.action = action;
		this.repositoryService = repositoryService;
		this.repositoryTechLanguageService = repositoryTechLanguageService;
		this.repositoryPullRequestService = repositoryPullRequestService;
	}

	// @Test
	// public void USER_REPOSITORIES_REQUEST_WITH_SAVE() {
	// 	RepositoryDto[] repoDtos = action.getMemberRepository("", "Djunnni");
	// 	for (RepositoryDto repo : repoDtos) {
	// 		try {
	// 			repositoryService.saveOrUpdateRepository(repo);
	// 		} catch (Exception e) {
	// 			System.out.println(e.toString());
	// 			System.out.println(repo);
	// 		}
	// 	}
	// }
	//
	// @Test
	// public void USER_REPOSITORIES_REQUEST_AND_TECH_LANGUAGE() {
	// 	RepositoryDto[] repoDtos = action.getMemberRepository("", "Djunnni");
	// 	for (RepositoryDto repo : repoDtos) {
	// 		try {
	// 			Map<String, Long> languages = action.getRepositoryLanguages(repo.getFullName());
	// 			Repository repository = repositoryService.saveOrUpdateRepository(repo);
	// 			repositoryTechLanguageService.fetchTechLanguage(repository, languages);
	// 		} catch (Exception e) {
	// 			e.printStackTrace();
	// 			System.out.println(repo);
	// 		}
	// 	}
	// }
	//
	// @Test
	// public void REPOSITORY_COMMIT_REQUEST_TEST() {
	// 	CommitDto[] commits = action.getRepositoryCommits("Djunnni/Algorithm");
	//
	// 	System.out.println(Arrays.toString(commits));
	// }
	//
	// @Test
	// public void REPOSITORY_LANGUAGE_REQUEST_TEST() {
	// 	Map<String, Long> languages = action.getRepositoryLanguages("Djunnni/Algorithm");
	// 	System.out.println(languages);
	// }
	//
	// @Test
	// public void USER_INFO_REQUEST_TEST() {
	// 	UserDto userDto = action.getMemberInfo("Djunnni");
	// 	System.out.println(userDto);
	// }
	// @Test
	// public void REPOSITORY_PULL_REQUEST_TEST() {
	// 	RepositoryDto[] repoDtos = action.getMemberRepository("", "Djunnni");
	// 	for(RepositoryDto repositoryDto : repoDtos) {
	// 		Repository repository = repositoryService.findById(repositoryDto.getId());
	// 		PullRequest[] pullRequests = Arrays.stream(action.getRepositoryPulls(repositoryDto.getFullName())).map(
	// 			prDto -> PullRequestMapper.toPullRequest(prDto)
	// 		).toArray(PullRequest[]::new);
	//
	// 		repositoryPullRequestService.save(repository, pullRequests);
	// 	}
	// }
}
