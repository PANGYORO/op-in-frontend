package com.c211.opinbackend.auth.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	public JwtFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		// 헤더에서 JWT 를 받아옵니다.
		String accessToken = resolveAccessToken(request);
		String refreshToken = resolveRefreshToken(request);

		// 유효한 토큰인지 확인합니다.
		if (accessToken != null) {
			// 어세스 토큰이 유효한 상황
			if (tokenProvider.validateToken(accessToken)) {
				Authentication authentication = tokenProvider.getAuthentication(accessToken);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			// 어세스 토큰이 만료된 상황 | 리프레시 토큰 또한 존재하는 상황
			else if (!tokenProvider.validateToken(accessToken) && refreshToken != null) {
				// 재발급 후, 컨텍스트에 다시 넣기
				// 리프레시 토큰 검증
				boolean validateRefreshToken = tokenProvider.validateToken(refreshToken);
				// 리프레시 토큰 저장소 존재유무 확인
				// boolean isRefreshToken = tokenProvider.existsRefreshToken(refreshToken);
				if (validateRefreshToken) {
					// 리프레시 토큰으로 이메일 & 권한 정보 가져오기
					Claims claims = tokenProvider.getClaims(refreshToken);
					String email = claims.get("email").toString();
					String authorities = claims.get("auth").toString();

					// 토큰 발급
					String newAccessToken = tokenProvider.createAccessToken(email, authorities);

					// 헤더에 어세스 토큰 추가
					response.setHeader("authorization", "bearer " + newAccessToken);

					//쿠키에 토큰 추가
					// 쿠키 생성
					Cookie cookie = new Cookie("accessToken", newAccessToken);
					cookie.setPath("/");
					response.addCookie(cookie);

					// 컨텍스트에 넣기
					Authentication authentication = tokenProvider.getAuthentication(newAccessToken);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}

		chain.doFilter(request, response);
	}

	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public String resolveAccessToken(HttpServletRequest request) {
		if (request.getHeader("authorization") != null)
			return request.getHeader("authorization").substring(7);
		return null;
	}

	public String resolveRefreshToken(HttpServletRequest request) {
		if (request.getHeader("refreshToken") != null)
			return request.getHeader("refreshToken").substring(7);
		return null;
	}

}
