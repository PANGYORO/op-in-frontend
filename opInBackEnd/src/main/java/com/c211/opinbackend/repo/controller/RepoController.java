package com.c211.opinbackend.repo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c211.opinbackend.auth.model.request.MemberEmailRequest;
import com.c211.opinbackend.repo.model.repository.RepositoryDto;
import com.c211.opinbackend.repo.service.repo.RepositoryService;
import com.c211.opinbackend.repo.service.repoTechlag.RepoTechLanguageService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/repo")
@Slf4j
@AllArgsConstructor
public class RepoController {
	private final RepositoryService repositoryService;
	private final RepoTechLanguageService repoTechLanguageService;

	@PostMapping
	public ResponseEntity<?> getReposByEmail(@RequestBody MemberEmailRequest emailRequest) {
		String email = emailRequest.getEmail();
		List<RepositoryDto> repositoryDtoList = repositoryService.finRepositoryListByMember(email);
		return new ResponseEntity<>(repositoryDtoList, HttpStatus.OK);
	}

}
