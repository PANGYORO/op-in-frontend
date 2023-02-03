package com.c211.opinbackend.auth.controller;

import static com.c211.opinbackend.exception.member.MemberExceptionEnum.*;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.c211.opinbackend.auth.constant.GitHub;
import com.c211.opinbackend.auth.entity.Role;
import com.c211.opinbackend.auth.jwt.JwtFilter;
import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.auth.model.TokenDto;
import com.c211.opinbackend.auth.model.request.MemberEmailRequest;
import com.c211.opinbackend.auth.model.request.MemberJoinRequest;
import com.c211.opinbackend.auth.model.request.MemberLoginRequest;
import com.c211.opinbackend.auth.model.request.MemberNicknameRequest;
import com.c211.opinbackend.auth.model.request.OAuthAccessTokenRequest;
import com.c211.opinbackend.auth.model.response.MypageResponse;
import com.c211.opinbackend.auth.model.response.OAuthAccessTokenResponse;
import com.c211.opinbackend.auth.model.response.OAuthUserInfoResponse;
import com.c211.opinbackend.auth.service.MemberService;
import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.util.RandomString;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class MemberController {
	private static final RestTemplate restTemplate = new RestTemplate();
	private final String clientId;
	private final String clientSecret;

	MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService,
		@Value("${security.oauth.github.client-id}") String clientId,
		@Value("${security.oauth.github.client-secret}") String clientSecret
	) {
		this.memberService = memberService;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	@PostMapping("/getMember")
	public ResponseEntity<?> getMemberInfo(@RequestBody MemberEmailRequest request) throws Exception {
		MypageResponse mypageResponse = memberService.getMemberInfo(request.getEmail());
		return new ResponseEntity<MypageResponse>(mypageResponse, HttpStatus.OK);
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
		try {
			TokenDto token = memberService.authorize(request.getEmail(), request.getPassword());

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.getAccessToken());

			return new ResponseEntity<TokenDto>(token, httpHeaders, HttpStatus.OK);
		} catch(Exception e) {
			throw new MemberRuntimeException(MEMBER_WRONG_EXCEPTION);
		}
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

	@GetMapping("/github")
	public void redirectGithub(HttpServletResponse response, @RequestParam("redirect") String redirect) throws IOException {
		response.sendRedirect(GitHub.AUTHORIZE_URL + "?client_id=" + clientId + "&redirect=" + redirect);
	}

	@GetMapping("/github/callback")
	public ResponseEntity<MemberDto> getUserInfo(@RequestParam String code) {
		String accessToken = getAccessToken(code);
		MemberDto user = getGitHubInfo(accessToken);
		return new ResponseEntity<>(user, HttpStatus.OK);


	}

	private String getAccessToken(String code) {
		return restTemplate.postForEntity(GitHub.ACCESS_TOKEN_URL,
			new OAuthAccessTokenRequest(clientId, clientSecret, code),
			OAuthAccessTokenResponse.class
		).getBody().getAccessToken();
	}

	private MemberDto getGitHubInfo(String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(accessToken);
		HttpEntity<Void> request = new HttpEntity<>(headers);

		OAuthUserInfoResponse res = restTemplate
			.exchange(GitHub.USER_INFO_URL,
				HttpMethod.GET,
				request,
				OAuthUserInfoResponse.class
			).getBody();

		return MemberDto.builder()
			.githubId(res.getId())
			.githubToken(accessToken)
			.githubSyncFl(true)
			.email(res.getEmail())
			.nickname(res.getLogin() + "." + RandomString.generateNumber())
			.avataUrl(res.getAvatar())
			.role(Role.ROLE_USER)
			.build();
	}
}
