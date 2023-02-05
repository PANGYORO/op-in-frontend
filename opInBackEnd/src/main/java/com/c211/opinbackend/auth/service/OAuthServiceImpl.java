package com.c211.opinbackend.auth.service;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.c211.opinbackend.auth.constant.GitHub;
import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.entity.Role;
import com.c211.opinbackend.auth.jwt.TokenProvider;
import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.auth.model.TokenDto;
import com.c211.opinbackend.auth.model.response.OAuthAccessTokenResponse;
import com.c211.opinbackend.auth.repository.MemberRepository;
import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.util.RandomString;

@Service
public class OAuthServiceImpl implements OAuthService {
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private static final Logger logger = LoggerFactory.getLogger(OAuthServiceImpl.class);
	private final String clientId;
	private final String clientSecret;
	MemberRepository memberRepository;

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public OAuthServiceImpl(
		PasswordEncoder passwordEncoder,
		TokenProvider tokenProvider,
		AuthenticationManagerBuilder authenticationManagerBuilder,
		MemberRepository memberRepository,
		@Value("${security.oauth.github.client-id}") String clientId,
		@Value("${security.oauth.github.client-secret}") String clientSecret) {
		this.memberRepository = memberRepository;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.tokenProvider = tokenProvider;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public String getRedirectURL() {
		return GitHub.AUTHORIZE_URL + "?client_id=" + clientId;
	}

	@Override
	public TokenDto login(String code) {
		OAuthAccessTokenResponse tokenResponse = getToken(code);
		MemberDto memberDto = getUserProfile(tokenResponse);
		logger.info("memberDTO: {}", memberDto);
		Member member = saveOrUpdate(memberDto);

		TokenDto token = authorize(member);

		return token;
	}

	public TokenDto authorize(Member member) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
			member.getEmail(), member.getPassword());
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String authorities = getAuthorities(authentication);

		return tokenProvider.createToken(member, authorities);
	}

	public String getAuthorities(Authentication authentication) {
		return authentication.getAuthorities()
			.stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(","));
	}

	@Transactional
	public Member saveOrUpdate(MemberDto memberDto) {
		// [TODO]: member 정보 업데이트
		// TODO: 2023/02/06 중복 체크
		if (memberRepository.existsByEmail(memberDto.getEmail())) {
			logger.info("fail on exist");
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_EXIST_EMAIL_EXCEPTION);
		}
		//Member member = memberRepository.findByGithubId(memberDto.getGithubId())
		//	.orElseGet(memberDto::toMember);
		//logger.info("member saveOrUpdate {}", member);
		return memberRepository.save(memberDto.toMember());
	}

	private MemberDto getUserProfile(OAuthAccessTokenResponse tokenResponse) {
		Map<String, Object> userAttributes = getUserAttributes(tokenResponse);
		logger.info(userAttributes.entrySet().toString());// 어떤 정보 가져오는지 확인
		// TODO: 2023/02/06 데브 올릴때 로그 지우기
		// TODO: 2023/02/06 더미 데이터 채우기
		return MemberDto.builder()
			.githubId(String.valueOf(userAttributes.get("id")))
			.githubToken(tokenResponse.getAccessToken())
			.githubSyncFl(true)
			.email((String)userAttributes.get("login") + "@gitHub.com")
			.password(passwordEncoder.encode("SsafyOut!123"))
			// TODO: 2023/02/06 로그인 이메일이 아니라 login 으로 변경필요 이메일이 없는 경우가 있다 - 일단 처리 했으나 프론트와 비밀번호 찾기같은거..
			.nickname(userAttributes.get("login") + "." + RandomString.generateNumber())
			.avatarUrl((String)userAttributes.get("avatar_url"))
			.role(Role.ROLE_USER)
			.build();
	}

	private Map<String, Object> getUserAttributes(OAuthAccessTokenResponse response) {
		return WebClient.create()
			.get()
			.uri(GitHub.USER_INFO_URL)
			.headers(header -> header.setBearerAuth(response.getAccessToken()))
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
			})
			.block();
	}

	private OAuthAccessTokenResponse getToken(String code) {
		return WebClient.create()
			.post()
			.uri(GitHub.ACCESS_TOKEN_URL)
			.headers(header -> {
				header.setBasicAuth(clientId, clientSecret);
				header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
				header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
			}).bodyValue(tokenRequest(code))
			.retrieve()
			.bodyToMono(OAuthAccessTokenResponse.class)
			.block();
	}

	private MultiValueMap<String, String> tokenRequest(String code) {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("code", code);
		//		formData.add("redirect_uri", "/");

		return formData;
	}
}
