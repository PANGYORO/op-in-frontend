package com.c211.opinbackend.search.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c211.opinbackend.search.dto.MemberDto;
import com.c211.opinbackend.search.dto.RepositoryResponseDto;
import com.c211.opinbackend.search.dto.SearchQueryRequest;
import com.c211.opinbackend.search.service.PostService;
import com.c211.opinbackend.search.service.RepoService;
import com.c211.opinbackend.search.service.UserService;
import com.c211.opinbackend.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

	private final PostService postService;
	private final RepoService repoService;
	private final UserService userService;

	@GetMapping("/users")
	public ResponseEntity<?> searchUsers(SearchQueryRequest request) {
		// [TODO]: Pageable 구현
		// PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());

		String userEmail = SecurityUtil.getCurrentUserId().orElse(null);
		List<MemberDto> results = userService.search(request.getQuery(), userEmail);

		return ResponseEntity.ok().body(results);
	}

	@GetMapping("/repos")
	public ResponseEntity<?> searchRepos(SearchQueryRequest request) {
		List<RepositoryResponseDto> result = repoService.search(request.getQuery());
		return ResponseEntity.ok().body(result);
	}

	@GetMapping("/posts")
	public ResponseEntity<?> searchPosts(SearchQueryRequest request) {
		return ResponseEntity.ok().body(new ArrayList<>());
	}

}
