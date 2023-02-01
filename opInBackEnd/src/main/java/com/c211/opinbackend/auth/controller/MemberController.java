package com.c211.opinbackend.auth.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.c211.opinbackend.auth.entity.Role;
import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.auth.model.TokenDto;
import com.c211.opinbackend.auth.model.request.MemberEmailRequest;
import com.c211.opinbackend.auth.model.request.MemberJoinRequest;
import com.c211.opinbackend.auth.model.request.MemberLoginRequest;
import com.c211.opinbackend.auth.model.request.MemberNicknameRequest;
import com.c211.opinbackend.auth.service.MemberService;
import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.jwt.JwtFilter;
import com.c211.opinbackend.jwt.TokenProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class MemberController {
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	private static final RestTemplate restTemplate = new RestTemplate();

	MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService,
		TokenProvider tokenProvider,
		AuthenticationManagerBuilder authenticationManagerBuilder
	) {
		this.memberService = memberService;
		this.tokenProvider = tokenProvider;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
	}

	@PostMapping("/getMember")
	public ResponseEntity<?> getMemberInfo(@RequestBody MemberEmailRequest request) throws Exception {
		MemberDto memberDto = memberService.getMemberInfo(request.getEmail());

		return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
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

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody MemberLoginRequest request) {
		TokenDto token = memberService.authorize(request.getEmail(), request.getPassword());

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.getAccessToken());

		return new ResponseEntity<TokenDto>(token, httpHeaders, HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<Boolean> signUp(@RequestBody MemberJoinRequest request) throws Exception {

		// 이메일 형식에 맞는지 체크 (형식 : @, . 을 포함하는지 확인)
		// 이메일 정규식 => /\S+@\S+.\S+/
		String email = request.getEmail();
		String emailPattern = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
		boolean emailRegex = Pattern.matches(emailPattern, email);
		if (!emailRegex) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_TYPE_EXCEPTION);
		}

		// 비밀번호 : 8~16자 영문 대 소문자, 숫자, 특수문자를 모두 포함
		String password = request.getPassword();
		String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#?!@$%^&*-]).{8,16}$";
		boolean passwordRegex = Pattern.matches(passwordPattern, password);
		if (!passwordRegex) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_PASSWORD_TYPE_EXCEPTION);
		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		MemberDto joinMember = MemberDto.builder()
			.email(email)
			.password(passwordEncoder.encode(password))
			.nickname(request.getNickname())
			.role(Role.ROLE_USER)
			.build();

		memberService.signUp(joinMember);

		return ResponseEntity.ok(true);

	}

}
