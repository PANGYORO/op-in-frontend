package com.c211.opinbackend.auth.controller;

import static com.c211.opinbackend.exception.member.MemberExceptionEnum.*;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.c211.opinbackend.auth.constant.GitHub;
import com.c211.opinbackend.auth.entity.Role;
import com.c211.opinbackend.auth.jwt.JwtFilter;
import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.auth.model.TokenDto;
import com.c211.opinbackend.auth.model.request.OAuthAccessTokenRequest;
import com.c211.opinbackend.auth.model.response.OAuthAccessTokenResponse;
import com.c211.opinbackend.auth.service.OAuthService;
import com.c211.opinbackend.auth.service.OAuthServiceImpl;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.util.RandomString;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

	private static final Logger logger = LoggerFactory.getLogger(OAuthController.class);
	OAuthService oAuthService;

	@Autowired
	public OAuthController(OAuthServiceImpl oAuthService) {
		this.oAuthService = oAuthService;
	}

	@GetMapping("/redirect/github")
	public void redirectGithub(HttpServletResponse response, @RequestParam(value = "redirect_uri", required = false) String redirectUri) throws IOException {
		response.sendRedirect(oAuthService.getRedirectURL(redirectUri));
	}

	@GetMapping("/login/github")
	public void getUserInfo(@RequestParam String code, @RequestParam(value = "redirect_uri", required = false) String redirectUri, HttpServletResponse response) {
		try {
			TokenDto token = oAuthService.login(code, redirectUri);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.getAccessToken());

			Cookie accessTokenCookie = new Cookie("access_token", token.getAccessToken());
			Cookie refreshTokenCookie = new Cookie("refresh_token", token.getRefreshToken());
			accessTokenCookie.setPath("/");
			refreshTokenCookie.setPath("/");

			response.addCookie(accessTokenCookie);
			response.addCookie(refreshTokenCookie);

			response.sendRedirect(redirectUri);
		} catch(Exception e) {
			e.printStackTrace();
			throw new MemberRuntimeException(MEMBER_WRONG_EXCEPTION);
		}

	}
}
