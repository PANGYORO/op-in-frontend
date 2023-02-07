package com.c211.opinbackend.auth.service;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.c211.opinbackend.auth.jwt.TokenProvider;
import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.auth.model.TokenDto;
import com.c211.opinbackend.auth.model.response.OAuthAccessTokenResponse;
import com.c211.opinbackend.constant.GitHub;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.Role;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.util.RandomString;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OAuthServiceImpl implements OAuthService {
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
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

	/**
	 * github OAuth를 위한 Redirect 주소를 리턴합니다.
	 *
	 * @return
	 */
	@Override
	public String getRedirectUrl(String redirectUri) {

		return GitHub.AUTHORIZE_URL + "?client_id=" + clientId
			+ (redirectUri != null ? "&redirect_uri=" + redirectUri : "");
	}

	@Override
	public TokenDto login(String code, String redirectUri) {
		OAuthAccessTokenResponse tokenResponse = getToken(code, redirectUri);
		MemberDto memberDto = getUserProfile(tokenResponse);
		Member member = saveOrUpdate(memberDto);

		TokenDto token = authorize(member);

		return token;
	}

	public TokenDto authorize(Member member) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
			member.getEmail(), "");
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String authorities = getAuthorities(authentication);

		return tokenProvider.createToken(member.getEmail(), authorities);
	}

	public String getAuthorities(Authentication authentication) {
		return authentication.getAuthorities()
			.stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(","));
	}

	/**
	 * 깃헙 아이디가 이미 등록되어 있다면, 깃헙의 정보를 업데이트 시켜줍니다.
	 *
	 * @param memberDto
	 * @return member
	 */
	@Transactional
	public Member saveOrUpdate(MemberDto memberDto) {
		Member member = memberRepository.findByGithubId(memberDto.getGithubId())
			.map(entity -> entity.fetch(memberDto.getGithubToken(), memberDto.getAvatarUrl()))
			.orElseGet(memberDto::toMember);

		return memberRepository.save(member);
	}

	/**
	 * Github으로부터 Member의 정보를 가져와 MemberDto로 넣어줍니다.
	 * email은 {github_id}@github.io 로 설정합니다 -> 이메일이 unique해야 하므로!
	 * nickname은 {github_id}.{random숫자 6자리}로 설정합니다.
	 *
	 * @param tokenResponse
	 * @return MemberDto
	 */
	private MemberDto getUserProfile(OAuthAccessTokenResponse tokenResponse) {
		Map<String, Object> userAttributes = getUserAttributes(tokenResponse);
		return MemberDto.builder()
			.githubId(String.valueOf(userAttributes.get("id")))
			.githubToken(tokenResponse.getAccessToken())
			.githubSyncFl(true)
			.password(new BCryptPasswordEncoder().encode(""))
			.email(userAttributes.get("id") + "@github.io")
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

	private OAuthAccessTokenResponse getToken(String code, String redirectUri) {
		return WebClient.create()
			.post()
			.uri(GitHub.ACCESS_TOKEN_URL)
			.headers(header -> {
				header.setBasicAuth(clientId, clientSecret);
				header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
				header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
			}).bodyValue(tokenRequest(code, redirectUri))
			.retrieve()
			.bodyToMono(OAuthAccessTokenResponse.class)
			.block();
	}

	/**
	 * OAuth 처리 과정에서 token을 가져올 때, formData를 전송합니다.
	 *
	 * @param code
	 * @return
	 */
	private MultiValueMap<String, String> tokenRequest(String code, String redirectUri) {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("code", code);
		// if(redirectUri != null) {
		// 	formData.add("redirect_uri", redirectUri);
		// }
		return formData;
	}
}
