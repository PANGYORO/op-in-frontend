package com.c211.opinbackend.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c211.opinbackend.auth.model.request.MemberEmailRequest;
import com.c211.opinbackend.auth.model.request.MemberLoginRequest;
import com.c211.opinbackend.auth.model.request.MemberNicknameRequest;
import com.c211.opinbackend.auth.model.request.MemberPasswordRequest;
import com.c211.opinbackend.auth.model.response.MypageResponse;
import com.c211.opinbackend.auth.model.response.TechLanguageResponse;
import com.c211.opinbackend.auth.service.MailService;
import com.c211.opinbackend.exception.api.ApiExceptionEnum;
import com.c211.opinbackend.exception.api.ApiRuntimeException;
import com.c211.opinbackend.exception.auth.AuthRuntimeException;
import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.member.model.request.TechLanguageRequest;
import com.c211.opinbackend.member.model.request.TopicAndLanguageRequest;
import com.c211.opinbackend.member.model.request.TopicRequest;
import com.c211.opinbackend.member.service.MemberService;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.util.SecurityUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {

	MemberService memberService;

	MailService mailService;

	@Autowired
	public MemberController(MemberService memberService,
		MailService mailService
	) {
		this.memberService = memberService;
		this.mailService = mailService;
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
	@PostMapping("/member/info")
	public ResponseEntity<?> getMemberLogin() {
		Member member = memberService.getMember();
		return new ResponseEntity<>(member, HttpStatus.OK);
	}

	// 임시 비밀번호 발급 이메일
	@PostMapping("/password/email")
	public ResponseEntity<?> changePwEmail(@RequestBody Map<String, String> email) {
		return ResponseEntity.ok(mailService.mailSend(email.get("email")));
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

	// 팔로우 취소
	@PostMapping("/follow/delete")
	public ResponseEntity<?> followDeleteMember(@RequestBody MemberNicknameRequest request) {
		return ResponseEntity.ok(memberService.followDeleteMember(request.getNickname()));
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
	
	// 로그인돼 있으면 tech language

}
