package com.c211.opinbackend.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.c211.opinbackend.auth.entity.Role;
import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.auth.model.TokenDto;
import com.c211.opinbackend.auth.model.request.MemberJoinRequest;
import com.c211.opinbackend.auth.model.request.MemberLoginRequest;
import com.c211.opinbackend.auth.service.MemberService;
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

	@PostMapping("/testauth")
	public ResponseEntity<?> testauth(@RequestBody MemberLoginRequest request) {

		return ResponseEntity.ok(null);
	}


	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody MemberLoginRequest request) {
		TokenDto token = memberService.authorize(request.getEmail(), request.getPassword());

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.getAccessToken());

		return new ResponseEntity<TokenDto>(token, httpHeaders, HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<Boolean> signUp(@RequestBody MemberJoinRequest request) {


		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		MemberDto joinMember = MemberDto.builder()
			.email(request.getEmail())
			.password(passwordEncoder.encode(request.getPassword()))
			.nickname(request.getNickname())
			.role(Role.ROLE_USER)
			.build();

		memberService.signUp(joinMember);

		return ResponseEntity.ok(true);

	}

}
