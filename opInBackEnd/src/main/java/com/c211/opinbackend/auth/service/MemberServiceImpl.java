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
import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
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

		// 아이디, 비밀번호 비교해서
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
	public Member signUp(MemberDto memberDto) throws Exception {

		// 이메일 중복 체크
		boolean existEmail = memberRepository.existsByEmail(memberDto.getEmail());
		if (existEmail) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_EXIST_EMAIL_EXCEPTION);
		}

		// 닉네임 중복 체크
		boolean existNickname = memberRepository.existsByNickname(memberDto.getNickname());
		if (existNickname) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_EXIST_NICKNAME_EXCEPTION);
		}

		Member member = Member.builder()
			.email(memberDto.getEmail())
			.password(memberDto.getPassword())
			.nickname(memberDto.getNickname())
			.githubSyncFl(false)
			.role(memberDto.getRole())
			.build();

		return memberRepository.save(member);
	}

	@Override
	public boolean existEmail(String email) {
		return memberRepository.existsByEmail(email);
	}

	@Override
	public boolean existNickname(String nickname) {
		return memberRepository.existsByNickname(nickname);
	}
}
