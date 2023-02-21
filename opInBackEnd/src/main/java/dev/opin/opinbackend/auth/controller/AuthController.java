package dev.opin.opinbackend.auth.controller;

import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.opin.opinbackend.auth.jwt.JwtFilter;
import dev.opin.opinbackend.auth.model.MemberDto;
import dev.opin.opinbackend.auth.model.TokenDto;
import dev.opin.opinbackend.auth.model.request.MemberJoinRequest;
import dev.opin.opinbackend.auth.model.request.MemberLoginRequest;
import dev.opin.opinbackend.auth.service.AuthService;
import dev.opin.opinbackend.exception.member.MemberExceptionEnum;
import dev.opin.opinbackend.exception.member.MemberRuntimeException;
import dev.opin.opinbackend.member.service.MemberService;
import dev.opin.opinbackend.persistence.entity.Role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;
	private final MemberService memberService;

	@Value("${jwt.access-token-validity-in-seconds}")
	private int accessTokenValidityInSeconds;

	@Value("${jwt.refresh-token-validity-in-seconds}")
	private int refreshTokenValidityInSeconds;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody MemberLoginRequest request, HttpServletResponse response) {
		if (memberService.isOAuthMember(request.getEmail())) {
			throw new MemberRuntimeException(MemberExceptionEnum.OAUTH_SIGNUP_USER_EXCEPTION);
		}
		TokenDto token = authService.authorize(request.getEmail(), request.getPassword());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.getAccessToken());

		// 쿠키 생성
		Cookie cookie = new Cookie("accessToken", token.getAccessToken());
		cookie.setPath("/");
		cookie.setMaxAge(accessTokenValidityInSeconds);
		response.addCookie(cookie);

		Cookie cookie2 = new Cookie("refreshToken", token.getRefreshToken());
		cookie2.setPath("/");
		cookie2.setMaxAge(refreshTokenValidityInSeconds);
		response.addCookie(cookie2);

		return new ResponseEntity<TokenDto>(token, httpHeaders, HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody MemberJoinRequest request) throws Exception {

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

		MemberDto joinMember = MemberDto.builder()
			.email(email)
			.password(password)
			.nickname(request.getNickname())
			.role(Role.ROLE_USER)
			.build();

		authService.signUp(joinMember);

		return new ResponseEntity<String>(email, HttpStatus.OK);

	}

	@PostMapping("/logout")
	public void logout(HttpServletResponse response) {
		SecurityContext context = SecurityContextHolder.getContext();
		SecurityContextHolder.clearContext();
		context.setAuthentication(null);

		// 쿠키 날려 버리기
		Cookie cookie = new Cookie("accessToken", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);

		Cookie cookie2 = new Cookie("refreshToken", null);
		cookie2.setMaxAge(0);
		cookie2.setPath("/");
		response.addCookie(cookie2);
	}

}
