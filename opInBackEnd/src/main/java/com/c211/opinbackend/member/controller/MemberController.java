package com.c211.opinbackend.member.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c211.opinbackend.auth.model.request.MemberEmailRequest;
import com.c211.opinbackend.auth.model.request.MemberLoginRequest;
import com.c211.opinbackend.auth.model.request.MemberNicknameRequest;
import com.c211.opinbackend.auth.model.request.MemberPasswordRequest;
import com.c211.opinbackend.auth.model.response.MypageResponse;
import com.c211.opinbackend.auth.service.MailService;
import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
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

	@PostMapping("/mypage")
	public ResponseEntity<?> getMemberInfo(@RequestBody MemberEmailRequest request) throws Exception {
		MypageResponse mypageResponse = memberService.getMemberInfo(request.getEmail());
		return new ResponseEntity<MypageResponse>(mypageResponse, HttpStatus.OK);
	}

	@PostMapping("/email/check")
	public ResponseEntity<?> existEmail(@RequestBody MemberEmailRequest request) throws Exception {
		boolean exist = memberService.existEmail(request.getEmail());
		return new ResponseEntity<Boolean>(exist, HttpStatus.OK);
	}

	@PostMapping("/nickname/check")
	public ResponseEntity<?> existNickname(@RequestBody MemberNicknameRequest request) throws Exception {
		boolean exist = memberService.existNickname(request.getNickname());
		return new ResponseEntity<Boolean>(exist, HttpStatus.OK);
	}

	@PostMapping("/nickname/put")
	public ResponseEntity<?> modifyNickname(@RequestBody MemberNicknameRequest request) {
		boolean exist = memberService.existNickname(request.getNickname());
		if (exist) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_EXIST_NICKNAME_EXCEPTION);
		}

		String username = SecurityUtil.getCurrentUserId().orElse(null);
		Member member = memberService.modifyNickname(request.getNickname(), username);
		return new ResponseEntity<String>(member.getNickname(), HttpStatus.OK);
	}

	@PostMapping("/password/put")
	public ResponseEntity<?> modifyPassword(@RequestBody MemberPasswordRequest request) {
		String username = SecurityUtil.getCurrentUserId().orElse(null);
		boolean val = memberService.modifyPassword(username, request.getPassword());
		return new ResponseEntity<Boolean>(val, HttpStatus.OK);
	}

	@PostMapping("/member/info")
	public ResponseEntity<?> getMemberLogin() {
		Member member = memberService.getMember();
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}

	@PostMapping("/password/email")
	public ResponseEntity<?> changePwEmail(@RequestBody Map<String, String> email) {
		String temporaryPassword = mailService.mailSend(email.get("email"));
		return ResponseEntity.ok(memberService.modifyPassword(email.get("email"), temporaryPassword));
	}

	@PostMapping("/delete")
	public ResponseEntity<?> deleteMember(@RequestBody MemberLoginRequest request) {
		return ResponseEntity.ok(memberService.deleteMember(request.getEmail(), request.getPassword()));
	}

	@PostMapping("/gitMem/delete")
	public ResponseEntity<?> deleteGithubMember(@RequestBody MemberLoginRequest request) {
		log.info(request.getEmail());
		return ResponseEntity.ok(memberService.deleteGithubMember(request.getEmail()));
	}

}
