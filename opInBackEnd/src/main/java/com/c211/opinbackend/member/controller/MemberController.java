package com.c211.opinbackend.member.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.c211.opinbackend.auth.model.request.MemberEmailRequest;
import com.c211.opinbackend.auth.model.request.MemberLoginRequest;
import com.c211.opinbackend.auth.model.request.MemberNicknameRequest;
import com.c211.opinbackend.auth.model.request.MemberPasswordRequest;
import com.c211.opinbackend.auth.model.response.MypageResponse;
import com.c211.opinbackend.auth.service.MailService;
import com.c211.opinbackend.exception.api.ApiExceptionEnum;
import com.c211.opinbackend.exception.api.ApiRuntimeException;
import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.member.model.dto.MemberDto;
import com.c211.opinbackend.member.model.request.TechLanguageRequest;
import com.c211.opinbackend.member.model.request.TopicAndLanguageRequest;
import com.c211.opinbackend.member.model.request.TopicRequest;
import com.c211.opinbackend.member.model.response.FileUploadResponse;
import com.c211.opinbackend.member.service.MemberService;
import com.c211.opinbackend.member.service.S3FileUploadService;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.repository.RepositoryFollowRepository;
import com.c211.opinbackend.util.SecurityUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

	private final MemberService memberService;
	private final MailService mailService;
	private final S3FileUploadService s3FileUploadService;

	private final RepositoryFollowRepository repositoryFollowRepository;

	/**
	 * 로그인 되어 있다면 내정보를 가져올수 있는 api
	 * #68
	 *
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getMemberMyInfo() {
		MemberDto memberInfo = memberService.getMemberInfoBySecurityContext();
		return ResponseEntity.ok().body(memberInfo);
	}

	// 마이페이지 정보 리턴
	@PostMapping("/mypage")
	public ResponseEntity<?> getMemberInfo(@RequestBody MemberNicknameRequest request) throws Exception {
		MypageResponse mypageResponse = memberService.getMemberInfo(request.getNickname());
		return new ResponseEntity<>(mypageResponse, HttpStatus.OK);
	}

	// 이메일 중복확인
	@PostMapping("/email/check")
	public ResponseEntity<?> existEmail(@RequestBody MemberEmailRequest request) throws Exception {
		boolean exist = memberService.existEmail(request.getEmail());
		return new ResponseEntity<>(exist, HttpStatus.OK);
	}

	// 닉네임 중복확인
	@PostMapping("/nickname/check")
	public ResponseEntity<?> existNickname(@RequestBody MemberNicknameRequest request) throws Exception {
		boolean exist = memberService.existNickname(request.getNickname());
		return new ResponseEntity<>(exist, HttpStatus.OK);
	}

	// 닉네임 변경
	@PostMapping("/nickname/put")
	public ResponseEntity<?> modifyNickname(@RequestBody MemberNicknameRequest request) {
		boolean exist = memberService.existNickname(request.getNickname());
		if (exist) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_EXIST_NICKNAME_EXCEPTION);
		}

		String username = SecurityUtil.getCurrentUserId().orElse(null);
		Member member = memberService.modifyNickname(request.getNickname(), username);
		return new ResponseEntity<>(member.getNickname(), HttpStatus.OK);
	}

	// 비밀번호 변경
	@PostMapping("/password/put")
	public ResponseEntity<?> modifyPassword(@RequestBody MemberPasswordRequest request) {
		String username = SecurityUtil.getCurrentUserId().orElse(null);
		boolean val = memberService.modifyPassword(username, request.getPassword());
		return new ResponseEntity<>(val, HttpStatus.OK);
	}

	// 로그인할 때 정보 리턴
	@PostMapping("/info")
	public ResponseEntity<?> getMemberLogin() {
		Member member = memberService.getMember();
		return new ResponseEntity<>(member, HttpStatus.OK);
	}

	// 임시 비밀번호 발급 이메일
	@PostMapping("/password/email")
	public ResponseEntity<?> changePwEmail(@RequestBody Map<String, String> email) {
		return ResponseEntity.ok(memberService.changePwEmail(email.get("email")));
	}

	// 회원 탈퇴
	@PostMapping("/delete")
	public ResponseEntity<?> deleteMember(@RequestBody MemberLoginRequest request) {
		return ResponseEntity.ok(memberService.deleteMember(request.getEmail(), request.getPassword()));
	}

	@PostMapping("/gitMem/delete")
	public ResponseEntity<?> deleteGithubMember(@RequestBody MemberLoginRequest request) {
		return ResponseEntity.ok(memberService.deleteGithubMember(request.getEmail()));
	}

	// 팔로우
	@PostMapping("/follow")
	public ResponseEntity<?> followMember(@RequestBody MemberNicknameRequest request) {
		memberService.followMember(request.getNickname());
		return ResponseEntity.ok(true);
	}

	/**
	 * 맴버의 가 래포 팔로우
	 *
	 * @param repoId
	 * @return
	 */
	@PostMapping("/follow/repo/{repoId}")
	public ResponseEntity<?> followRepo(@PathVariable("repoId") Long repoId) {
		String memberEmail = SecurityUtil.getCurrentUserId().orElseThrow(() -> new MemberRuntimeException(
			MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION
		));
		Boolean saveState = memberService.followRepo(repoId, memberEmail);
		return ResponseEntity.ok().body(saveState);
	}

	// 팔로우 취소
	// TODO: 2023/02/12 추후 델리트 매소드로 바꾸면 좋을거 같습니다.
	@PostMapping("/follow/delete")
	public ResponseEntity<?> followDeleteMember(@RequestBody MemberNicknameRequest request) {
		return ResponseEntity.ok(memberService.followDeleteMember(request.getNickname()));
	}

	/**
	 * 래포아이디를 받아 팔로우 취소
	 *
	 * @param repoId
	 * @return 저장한 repoId
	 */
	@DeleteMapping("/follow/repo/{repoId}")
	public ResponseEntity<?> followRepoDeleteMember(@PathVariable Long repoId) {
		String memberEmail = SecurityUtil.getCurrentUserId().orElseThrow(() -> new MemberRuntimeException(
			MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION
		));
		Boolean delState = memberService.followDeleteRepo(repoId, memberEmail);
		return ResponseEntity.ok().body(delState);
	}

	/**
	 * 래포지토리를 팔로우 하고 있는지 체크
	 *
	 * @param repoId
	 * @return
	 */

	@GetMapping("/follow/repo/{repoId}")
	public ResponseEntity<?> checkFollowRepo(@PathVariable Long repoId) {
		String memberEmail = SecurityUtil.getCurrentUserId().orElseThrow(() -> new MemberRuntimeException(
			MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION
		));
		Boolean res = memberService.followCheckRepo(repoId, memberEmail);
		return ResponseEntity.ok().body(res);
	}

	//팔로우여부 확인 : true/ false
	@PostMapping("/follow/check")
	public ResponseEntity<?> isFollow(@RequestBody MemberNicknameRequest request) {
		return ResponseEntity.ok(memberService.isFollow(request.getNickname()));
	}

	// 회원가입 시 Topic & TechLanguage 저장
	@PostMapping("/topic/language/put")
	public ResponseEntity<?> saveSignUpTopicAndTechLanguage(@RequestBody TopicAndLanguageRequest request) {
		if (memberService.saveSignUpTopicAndTechLanguage(request.getEmail(), request.getTopic(), request.getLan())) {
			return ResponseEntity.ok(true);
		} else {
			throw new ApiRuntimeException(ApiExceptionEnum.API_WORK_FAILED_EXCEPTION);
		}
	}

	// Topic 따로 저장
	@PostMapping("/topic/put")
	public ResponseEntity<?> saveLoginTopic(@RequestBody TopicRequest request) {
		if (memberService.saveLoginTopic(request.getTitle())) {
			return ResponseEntity.ok(true);
		} else {
			throw new ApiRuntimeException(ApiExceptionEnum.API_WORK_FAILED_EXCEPTION);
		}
	}

	// Tech Language 따로 저장
	@PostMapping("/language/put")
	public ResponseEntity<?> saveLoginTechLanguage(@RequestBody TechLanguageRequest request) {
		if (memberService.saveLoginTechLanguage(request.getTitle())) {
			return ResponseEntity.ok(true);
		} else {
			throw new ApiRuntimeException(ApiExceptionEnum.API_WORK_FAILED_EXCEPTION);
		}
	}

	// 전체 tech language 가져오기
	@GetMapping("/language/all")
	public ResponseEntity<?> getListTechLanguage() {
		return new ResponseEntity<>(memberService.getListTechLanguage(), HttpStatus.OK);
	}

	// member - tech language 단건 삭제
	@PostMapping("/language/delete")
	public ResponseEntity<?> deleteLoginMemberTechLanguage(@RequestBody TechLanguageRequest request) {
		return ResponseEntity.ok(memberService.deleteLoginMemberTechLanguage(request.getTitle()));
	}

	// member - topic 단건 삭제
	@PostMapping("/topic/delete")
	public ResponseEntity<?> deleteLoginMemberTopic(@RequestBody TopicRequest request) {
		return ResponseEntity.ok(memberService.deleteLoginMemberTopic(request.getTitle()));
	}

	//유저 프로필 업로드
	@PostMapping("/profilePhoto")
	public ResponseEntity<?> uploadProfilePhoto(@RequestParam("profilePhoto") MultipartFile multipartFile) throws
		IOException {
		//S3 Bucket 내부에 "/profile"
		FileUploadResponse profile = s3FileUploadService.upload(multipartFile, "profile");
		return ResponseEntity.ok(profile);
	}

	@GetMapping("/repo/recommend")
	public ResponseEntity<?> getRecommendRepositories() {
		return new ResponseEntity<>(memberService.getRecommendRepositories(), HttpStatus.OK);
	}
}
