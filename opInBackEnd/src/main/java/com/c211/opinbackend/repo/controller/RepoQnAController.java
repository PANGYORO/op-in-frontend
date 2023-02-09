package com.c211.opinbackend.repo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.repo.model.requeset.RequestComment;
import com.c211.opinbackend.repo.model.requeset.RequestQnA;
import com.c211.opinbackend.repo.model.response.RepoQnAResponse;
import com.c211.opinbackend.repo.service.repo.RepoQnAService;
import com.c211.opinbackend.util.SecurityUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/qna")
@AllArgsConstructor
public class RepoQnAController {

	private final RepoQnAService repoQnAService;

	@PostMapping
	public ResponseEntity<?> createQnA(@RequestBody RequestQnA requestQnA) {
		String memberEmail = SecurityUtil.getCurrentUserId()
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		if (!repoQnAService.createRepoQnA(requestQnA, memberEmail)) {
			return ResponseEntity.badRequest().body(false);
		}
		return ResponseEntity.ok().body(true);
	}

	@PostMapping("/comment")
	public ResponseEntity<?> createComment(@RequestBody RequestComment comment) {
		String memberEmail = SecurityUtil.getCurrentUserId()
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		if (!repoQnAService.creatQnAComment(comment, memberEmail)) {
			return ResponseEntity.badRequest().body(false);
		}
		return ResponseEntity.ok().body(true);
	}

	@GetMapping("/repo/{repoId}")
	public ResponseEntity<?> getQnaList(@PathVariable Long repoId) {
		List<RepoQnAResponse> repoQnALIst = repoQnAService.getRepoQnALIst(repoId);
		return ResponseEntity.ok().body(repoQnALIst);
	}

}
