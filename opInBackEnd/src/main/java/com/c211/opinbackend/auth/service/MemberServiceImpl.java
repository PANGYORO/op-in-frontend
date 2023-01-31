package com.c211.opinbackend.auth.service;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.auth.model.TokenDto;
import com.c211.opinbackend.auth.repository.MemberRepository;
import com.c211.opinbackend.jwt.TokenProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final TokenProvider tokenProvider;

	MemberRepository memberRepository;

	@Autowired
	public MemberServiceImpl(MemberRepository memberRepository,
		AuthenticationManagerBuilder authenticationManagerBuilder,
		TokenProvider tokenProvider) {
		this.memberRepository = memberRepository;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.tokenProvider = tokenProvider;
	}

	@Override
	public TokenDto authorize(String email, String password) {

		boolean exist = memberRepository.existsByEmail(email);
		if (!exist) { // 테스트용 - id가 없으면,
			return new TokenDto("N", "N");
		}

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
			password);

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String authorities = getAuthorities(authentication);

		return tokenProvider.createToken(authentication.getName(), authorities);
	}

	// 권한 가져오기
	public String getAuthorities(Authentication authentication) {
		return authentication.getAuthorities()
			.stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(","));
	}

	@Override
	public Member signUp(MemberDto memberDto) {

		Member member = Member.builder()
			.email(memberDto.getEmail())
			.password(memberDto.getPassword())
			.nickname(memberDto.getNickname())
			.githubSyncFl(false)
			.role(memberDto.getRole())
			.build();

		return memberRepository.save(member);
	}
}
