package com.c211.opinbackend.repo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c211.opinbackend.auth.model.request.MemberEmailRequest;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/repo")
@Slf4j
public class RepoController {
	@PostMapping
	public ResponseEntity<?> getReposByEmail(@RequestBody MemberEmailRequest emailRequest) {
		log.info(emailRequest.getEmail());
		return null;
	}

	@GetMapping("/{email:.+}")
	// TODO: 2023/02/01 config추가
	
	public ResponseEntity<?> getReposByEmailTest(@PathVariable String email) {
		log.info(email);
		return null;
	}

}
