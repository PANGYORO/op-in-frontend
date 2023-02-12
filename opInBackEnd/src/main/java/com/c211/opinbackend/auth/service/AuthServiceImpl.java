package com.c211.opinbackend.auth.service;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.c211.opinbackend.auth.jwt.TokenProvider;
import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.auth.model.TokenDto;
import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.repository.BadgeRepository;
import com.c211.opinbackend.persistence.repository.CommentRepository;
import com.c211.opinbackend.persistence.repository.MemberBadgeRepository;
import com.c211.opinbackend.persistence.repository.MemberFollowRepository;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.persistence.repository.MemberTechLanguageRepository;
import com.c211.opinbackend.persistence.repository.MemberTopicRepository;
import com.c211.opinbackend.persistence.repository.RepoContributorRepository;
import com.c211.opinbackend.persistence.repository.RepoPostRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.persistence.repository.RepositoryFollowRepository;
import com.c211.opinbackend.persistence.repository.RepositoryPostMemberLikeRepository;
import com.c211.opinbackend.persistence.repository.TechLanguageRepository;
import com.c211.opinbackend.persistence.repository.TopicRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final TokenProvider tokenProvider;
	private final MemberRepository memberRepository;
	private final MemberFollowRepository memberFollowRepository;
	private final MemberBadgeRepository memberBadgeRepository;
	private final MemberTechLanguageRepository memberTechLanguageRepository;
	private final MemberTopicRepository memberTopicRepository;
	private final BadgeRepository badgeRepository;
	private final TopicRepository topicRepository;
	private final TechLanguageRepository techLanguageRepository;
	private final RepoRepository repoRepository;
	private final RepoPostRepository repoPostRepository;
	private final CommentRepository commentRepository;
	private final RepositoryPostMemberLikeRepository repositoryPostMemberLikeRepository;
	private final RepositoryFollowRepository repositoryFollowRepository;
	private final RepoContributorRepository repoContributorRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public TokenDto authorize(String email, String password) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		if (!passwordEncoder.matches(password, member.getPassword())) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_WRONG_EXCEPTION);
		}

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
			password);

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String authorities = getAuthorities(authentication);
		return tokenProvider.createToken(email, authorities);
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
			.password(passwordEncoder.encode(memberDto.getPassword()))
			.nickname(memberDto.getNickname())
			.githubSyncFl(false)
			.role(memberDto.getRole())
			.build();

		return memberRepository.save(member);
	}

}
