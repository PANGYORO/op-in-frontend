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
import com.c211.opinbackend.persistence.entity.RepositoryPost;
import com.c211.opinbackend.repo.model.requeset.CreatePostRequest;
import com.c211.opinbackend.repo.model.requeset.RequestCommentCreateToPost;
import com.c211.opinbackend.repo.model.requeset.RequestUpdatePost;
import com.c211.opinbackend.repo.model.response.RepoPostDetailResponse;
import com.c211.opinbackend.repo.model.response.RepoPostSimpleResponse;
import com.c211.opinbackend.repo.service.commnet.CommentService;
import com.c211.opinbackend.repo.service.mapper.RepoPostMapper;
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

	/**
	 * 포스트 만들기
	 *
	 * @param createPostRequest
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> writePost(@RequestBody CreatePostRequest createPostRequest) {
		try {
			String memberEmail = SecurityUtil.getCurrentUserId().orElseThrow(
				() -> new MemberRuntimeException(
					MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION)
			);
			RepositoryPost post = repositoryPostService.createPostToRepository(createPostRequest, memberEmail);

			return ResponseEntity.ok().body(RepoPostMapper.toSimpleResponse(post));
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(RepositoryExceptionEnum.REPOSITORY_POST_SAVE_EXCEPTION.getErrorMessage());
		}
	}

	/**
	 * 모든 포스트 조회
	 *
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getAllPosts() {
		try {
			List<RepoPostSimpleResponse> allPostList = repositoryPostService.getAllPost();
			return new ResponseEntity<Object>(allPostList, HttpStatus.OK);
		} catch (Exception exception) {
			return ResponseEntity.badRequest().body("조회에 실패 했습니다.");
		}
	}

	/**
	 * 래포지토리에 속한 포스트 가져오기
	 *
	 * @param repoId
	 * @return
	 */
	@GetMapping("/repo/{repoId}")
	public ResponseEntity<?> getRepoPosts(@PathVariable("repoId") Long repoId) {
		try {
			log.info(String.valueOf(repoId));
			List<RepoPostSimpleResponse> allPostList = repositoryPostService.getRepoAllPostList(repoId);
			return new ResponseEntity<Object>(allPostList, HttpStatus.OK);
		} catch (Exception exception) {
			return ResponseEntity.badRequest().body("조회에 실패 했습니다.");
		}
	}

	/**
	 * 맴버에 속한 포스트들 조회
	 *
	 * @param memberId
	 * @return
	 */
	@GetMapping("member/{nickName}")
	public ResponseEntity<?> getMembersRepo(@PathVariable String nickName) {
		return ResponseEntity.ok().body(repositoryPostService.getMembersRepoPost(nickName));
	}

	/**
	 * 특정 포스트 1개 자세하게 조회
	 *
	 * @param postId
	 * @return
	 */
	@GetMapping("/{postId}")
	public ResponseEntity<?> getDetailPost(@PathVariable("postId") Long postId) {
		RepoPostDetailResponse repoDetail = repositoryPostService.getRepoDetail(postId);
		return ResponseEntity.ok().body(repoDetail);
	}

	/**
	 * 댓글 저장
	 *
	 * @param requestCommentCreateToPost
	 * @return
	 */
	@PostMapping("/comment")
	public ResponseEntity<?> createCommentToPost(@RequestBody RequestCommentCreateToPost requestCommentCreateToPost) {
		String memberEmail = SecurityUtil.getCurrentUserId()
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_WRONG_EXCEPTION));
		commentService.createCommentToPost(memberEmail, requestCommentCreateToPost);

		return ResponseEntity.ok().body("댓글 저장 성공");

	}

	/**
	 * 포스트 업데이트
	 *
	 * @param post
	 * @return
	 */
	@PutMapping
	public ResponseEntity<?> updatePosts(@RequestBody RequestUpdatePost post) {
		repositoryPostService.update(post);
		return ResponseEntity.ok().body(true);
	}

	/**
	 * 포스트 지우기
	 *
	 * @param postId
	 * @return
	 */
	@PostMapping("/delete/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable("postId") Long postId) {
		if (repositoryPostService.deleteRepo(postId)) {
			return ResponseEntity.ok().body(postId);
		} else {
			return ResponseEntity.badRequest().body(postId);
		}
	}

}
