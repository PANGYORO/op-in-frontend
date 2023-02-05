package com.c211.opinbackend.auth.controller;

import static com.c211.opinbackend.exception.member.MemberExceptionEnum.*;

import java.io.IOException;

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
	public void redirectGithub(HttpServletResponse response) throws IOException {
		response.sendRedirect(oAuthService.getRedirectURL());
	}

	@GetMapping("/login/github")
	public ResponseEntity<TokenDto> getUserInfo(@RequestParam String code) {
		try {
			TokenDto token = oAuthService.login(code);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.getAccessToken());
			logger.info(token.getAccessToken());
			return new ResponseEntity<>(token, httpHeaders, HttpStatus.OK);
		} catch(Exception e) {
			logger.error(e.toString());
			throw new MemberRuntimeException(MEMBER_WRONG_EXCEPTION);
		}

	}
}
