package com.c211.opinbackend.auth.controller;

import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.auth.model.request.MemberJoinRequest;
import com.c211.opinbackend.auth.model.request.MemberLoginRequest;
import com.c211.opinbackend.auth.service.MemberService;

@RestController
@RequestMapping("/auth")
public class MemberController {

	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	private static final RestTemplate restTemplate = new RestTemplate();

	// private final String clientId;
	// private final String clientSecret;

	MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService,
		AuthenticationManagerBuilder authenticationManagerBuilder
		// @Value("${security.oauth.github.client-id}") String clientId,
		// @Value("${security.oauth.github.client-secret}") String clientSecret
	){
		this.memberService = memberService;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
		// this.clientId = clientId;
		// this.clientSecret = clientSecret;
	}


	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody MemberLoginRequest request){

		UsernamePasswordAuthenticationToken authenticationToken =
			new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

		System.out.println(authenticationToken);

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		System.out.println(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		//
		// String jwt = tokenProvider.createToken(authentication);
		//
		// HttpHeaders httpHeaders = new HttpHeaders();
		// httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
		//
		// return new ResponseEntity<TokenDto>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);

		// SecurityUtil.getCurrentMemberId()
		Member member = memberService.login(request.getEmail(), request.getPassword());

		if (member == null) {
			return ResponseEntity.ok().body("NOT FOUND");
		}
		return ResponseEntity.ok(member);
	}

	@PostMapping("/signup")
	public ResponseEntity<Boolean> signUp(@RequestBody MemberJoinRequest request) {

		// MemberRole role = new MemberRole();
		// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// request.setUpw(passwordEncoder.encode(member.getUpw()));
		// role.setRoleName("BASIC");
		// member.setRoles(Arrays.asList(role));
		// memberRepository.save(member);

		MemberDto joinMember = MemberDto.builder()
			.email(request.getEmail())
			.password(request.getPassword())
			.nickname(request.getNickname())
			.build();

		memberService.signUp(joinMember);

		return ResponseEntity.ok(true);

	}

}
