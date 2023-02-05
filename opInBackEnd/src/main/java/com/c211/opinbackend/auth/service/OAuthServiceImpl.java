package com.c211.opinbackend.auth.service;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.c211.opinbackend.auth.constant.GitHub;
import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.entity.Role;
import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.auth.model.TokenDto;
import com.c211.opinbackend.auth.model.response.OAuthAccessTokenResponse;
import com.c211.opinbackend.auth.repository.MemberRepository;
import com.c211.opinbackend.util.RandomString;

@Service
@Transactional
public class OAuthServiceImpl implements OAuthService {

	private static final Logger logger = LoggerFactory.getLogger(OAuthServiceImpl.class);
	private final String clientId;
	private final String clientSecret;
	MemberRepository memberRepository;
	MemberService memberService;

	@Autowired
	public OAuthServiceImpl(MemberRepository memberRepository,MemberServiceImpl memberService,@Value("${security.oauth.github.client-id}") String clientId,
		@Value("${security.oauth.github.client-secret}") String clientSecret) {
		this.memberRepository = memberRepository;
		this.memberService = memberService;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
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
		Member member = memberRepository.save(memberDto.toMember());
		logger.info("member: {}", member);
		TokenDto token = memberService.authorize(member.getEmail(), member.getPassword());

		return token;
	}

	private Member saveOrUpdate(MemberDto memberDto) {
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
			.email((String) userAttributes.get("email"))
			.nickname(userAttributes.get("login") + "." + RandomString.generateNumber())
			.avatarUrl((String) userAttributes.get("avatar_url"))
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
