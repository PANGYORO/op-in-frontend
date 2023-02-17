package com.c211.opinbackend.search.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.c211.opinbackend.search.dto.response.MemberDto;
import com.c211.opinbackend.search.dto.response.RepositoryDto;
import com.c211.opinbackend.search.dto.response.SearchPostDto;
import com.c211.opinbackend.search.dto.response.SearchQnaDto;
import com.c211.opinbackend.search.service.PostService;
import com.c211.opinbackend.search.service.QnaService;
import com.c211.opinbackend.search.service.RepoService;
import com.c211.opinbackend.search.service.UserService;
import com.c211.opinbackend.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

	private final PostService postService;
	private final RepoService repoService;
	private final UserService userService;
	private final QnaService qnaService;

	@GetMapping("/users")
	public ResponseEntity<?> searchUsers(
		@RequestParam(name = "query") String query,
		@PageableDefault(size = 10, page = 0) Pageable pageable
	) {
		String userEmail = SecurityUtil.getCurrentUserId().orElse(null);
		List<MemberDto> results = userService.search(userEmail, query, pageable);

		return ResponseEntity.ok().body(results);
	}

	@GetMapping("/repos")
	public ResponseEntity<?> searchRepos(
		@RequestParam(name = "query") String query,
		@PageableDefault(size = 10, page = 0) Pageable pageable
	) {
		List<RepositoryDto> result = repoService.search(query, pageable);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping("/repos/{repoId}/posts")
	public ResponseEntity<?> searchRepoPosts(
		@PathVariable Long repoId,
		@RequestParam(name = "query") String query,
		@PageableDefault(size = 10, page = 0) Pageable pageable
	) {
		List<SearchPostDto> result = postService.searchInRepo(repoId, query, pageable);

		return ResponseEntity.ok().body(result);
	}

	@GetMapping("/repos/{repoId}/qnas")
	public ResponseEntity<?> searchRepoQnas(
		@PathVariable Long repoId,
		@RequestParam(name = "query") String query,
		@PageableDefault(size = 10, page = 0) Pageable pageable
	) {
		List<SearchQnaDto> result = qnaService.searchInRepo(repoId, query, pageable);

		return ResponseEntity.ok().body(result);
	}

	@GetMapping("/posts")
	public ResponseEntity<?> searchPosts(
		@RequestParam(name = "query") String query,
		@PageableDefault(size = 10, page = 0) Pageable pageable
	) {
		List<SearchPostDto> result = postService.search(query, pageable);
		return ResponseEntity.ok().body(result);
	}

}
