package com.c211.opinbackend.repo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.repo.model.requeset.CreatePostRequest;
import com.c211.opinbackend.repo.service.repo.RepositoryPostService;
import com.c211.opinbackend.util.SecurityUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/post")
@RestController
@AllArgsConstructor
public class RepoPostController {

	private final RepositoryPostService repositoryPostService;

	@PostMapping()
	public ResponseEntity<?> writePost(@RequestBody CreatePostRequest createPostRequest) {
		log.info(createPostRequest.getContent());

		// TODO: 2023-02-06 이부분 나중에 유틸 들어오면 유틸로 바꿔줘야합니다.
		String memberEmail = SecurityUtil.getCurrentUserId().orElseThrow(
			() -> new MemberRuntimeException(
				MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION)
		);
		// 등록할 래포진토리 아이디필요

		log.info(memberEmail);
		repositoryPostService.createPostToRepository(createPostRequest, memberEmail);
		return null;
	}
	
}
