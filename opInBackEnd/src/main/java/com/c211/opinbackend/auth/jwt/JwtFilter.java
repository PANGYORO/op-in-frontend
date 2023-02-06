package com.c211.opinbackend.auth.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

	public static final String AUTHORIZATION_HEADER = "Authorization";
	private final TokenProvider tokenProvider;

	public JwtFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {
		// HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		if (request.getMethod().equals("OPTIONS")) {
			return;
		}
		String jwt = resolveToken(request);
		String requestUri = request.getRequestURI();
		log.debug("doFilter 들어옴");

		if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
			Authentication authentication = tokenProvider.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri : {}", authentication.getName(), requestUri);
		} else {
			log.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestUri);
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

}
