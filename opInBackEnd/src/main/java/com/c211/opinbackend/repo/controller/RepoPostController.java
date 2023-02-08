package com.c211.opinbackend.repo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.exception.repositroy.RepositoryExceptionEnum;
import com.c211.opinbackend.repo.model.requeset.CreatePostRequest;
import com.c211.opinbackend.repo.model.requeset.RequestCommentCreateToPost;
import com.c211.opinbackend.repo.model.response.RepoPostDetailResponse;
import com.c211.opinbackend.repo.model.response.RepoPostSimpleResponse;
import com.c211.opinbackend.repo.service.commnet.CommentService;
import com.c211.opinbackend.repo.service.repo.RepositoryPostService;
import com.c211.opinbackend.util.SecurityUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// TODO: 2023/02/07 권한 검사 필요
@Slf4j
@RequestMapping("/post")
@RestController
@AllArgsConstructor
public class RepoPostController {

	private final RepositoryPostService repositoryPostService;
	private final CommentService commentService;

	// TODO: 2023/02/07 애러처리를 어떻게 해야할까
	@PostMapping
	public ResponseEntity<?> writePost(@RequestBody CreatePostRequest createPostRequest) {
		try {
			String memberEmail = SecurityUtil.getCurrentUserId().orElseThrow(
				() -> new MemberRuntimeException(
					MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION)
			);
			repositoryPostService.createPostToRepository(createPostRequest, memberEmail);
			return ResponseEntity.ok(HttpStatus.ACCEPTED);
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(RepositoryExceptionEnum.REPOSITORY_POST_SAVE_EXCEPTION.getErrorMessage());
		}
	}

	// 포스트 글들을 가져오는 api
	@GetMapping
	public ResponseEntity<?> getPosts() {
		try {
			List<RepoPostSimpleResponse> allPostList = repositoryPostService.getAllPostList();
			return new ResponseEntity<Object>(allPostList, HttpStatus.OK);
		} catch (Exception exception) {
			return ResponseEntity.badRequest().body("조회에 실패 했습니다.");
		}
	}

	@GetMapping("/{postId}")
	public ResponseEntity<?> getDetailPost(@PathVariable("postId") Long postId) {
		RepoPostDetailResponse repoDetail = repositoryPostService.getRepoDetail(postId);
		return ResponseEntity.ok().body(repoDetail);
	}

	@PostMapping("/comment")
	public ResponseEntity<?> createCommentToPost(@RequestBody RequestCommentCreateToPost requestCommentCreateToPost) {
		String memberEmail = SecurityUtil.getCurrentUserId()
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_WRONG_EXCEPTION));
		commentService.createCommentToPost(memberEmail, requestCommentCreateToPost);

		return ResponseEntity.ok().body("댓글 저장 성공");

	}

	@PutMapping
	public ResponseEntity<?> updatePosts() {
		return null;
	}

	@PostMapping("/delete/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable("postId") Long postId) {
		if (repositoryPostService.deleteRepo(postId)) {
			return ResponseEntity.ok().body(postId);
		} else {
			return ResponseEntity.badRequest().body(postId);
		}
	}

}
