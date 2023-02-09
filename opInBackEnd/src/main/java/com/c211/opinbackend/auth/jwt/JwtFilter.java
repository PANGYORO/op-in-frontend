package com.c211.opinbackend.auth.jwt;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

	public static final String AUTHORIZATION_HEADER = "Authorization";

	private TokenProvider tokenProvider;

	@Value("${jwt.access-token-validity-in-seconds}")
	private long accessTokenValidityInSeconds;

	public JwtFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		String accessToken = null;
		String refreshToken = null;

		try {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("accessToken".equals(cookie.getName()))
						accessToken = cookie.getValue();
					if ("refreshToken".equals(cookie.getName()))
						refreshToken = cookie.getValue();
				}
			}
		}
		catch (Exception ex) {
			throw new RemoteException("JwtFilter -> get Cookies error");
		}

		// 리프레쉬 토큰이 없으면 재로그인 요청이 필요함 -> 결국 인증 로직 안 탐
		if (refreshToken != null) {
			// 어세스 토큰이 있다면
			if (accessToken != null) {
				//어세스 토큰 유효성 검사
				if (tokenProvider.validateToken(accessToken)) {
					Authentication authentication = tokenProvider.getAuthentication(accessToken);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			// 어세스 토큰이 없다면
			} else {
				// 리프레쉬 토큰 유효성 검사
				if (tokenProvider.validateToken(refreshToken)) {
					// 리프레시 토큰으로 이메일 & 권한 정보 가져오기
					Claims claims = tokenProvider.getClaims(refreshToken);
					String email = claims.get("email").toString();
					String authorities = claims.get("auth").toString();

					// 토큰 발급
					String newAccessToken = tokenProvider.createAccessToken(email, authorities);

					// 헤더에 어세스 토큰 추가
					response.setHeader("authorization", "bearer " + newAccessToken);

					//쿠키에 토큰 추가
					Cookie cookie = new Cookie("accessToken", newAccessToken);
					cookie.setPath("/");
					cookie.setMaxAge(((int)accessTokenValidityInSeconds/1000)-1);
					response.addCookie(cookie);

					// 컨텍스트에 넣기
					Authentication authentication = tokenProvider.getAuthentication(newAccessToken);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}

		chain.doFilter(request, response);
	}

}
