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
		logger.info("in authorsize");
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
			member.getEmail(), member.getPassword());
		logger.info("in authorsize 1");
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		logger.info("in authorsize2");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		logger.info("in authorsize3");
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
		//Member member = memberRepository.findByGithubId(memberDto.getGithubId())
		//	.orElseGet(memberDto::toMember);
		//logger.info("member saveOrUpdate {}", member);
		return memberRepository.save(memberDto.toMember());
	}

	private MemberDto getUserProfile(OAuthAccessTokenResponse tokenResponse) {
		Map<String, Object> userAttributes = getUserAttributes(tokenResponse);
		return MemberDto.builder()
			.githubId(String.valueOf(userAttributes.get("id")))
			.githubToken(tokenResponse.getAccessToken())
			.githubSyncFl(true)
			.email((String)userAttributes.get("email"))
			.password(passwordEncoder.encode("SsafyOut!123"))
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
