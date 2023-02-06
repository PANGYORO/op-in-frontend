package com.c211.opinbackend.auth.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

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
			if (StringUtils.hasText(accessToken) && tokenProvider.validateToken(accessToken)) {
				Authentication authentication = tokenProvider.getAuthentication(accessToken);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			// 어세스 토큰이 만료된 상황 | 리프레시 토큰 또한 존재하는 상황
			else if (!tokenProvider.validateToken(accessToken) && refreshToken != null) {
				// 재발급 후, 컨텍스트에 다시 넣기
				/// 리프레시 토큰 검증
				boolean validateRefreshToken = tokenProvider.validateToken(refreshToken);
				/// 리프레시 토큰 저장소 존재유무 확인
				boolean isRefreshToken = tokenProvider.existsRefreshToken(refreshToken);
				if (validateRefreshToken && isRefreshToken) {
					/// 리프레시 토큰으로 이메일 정보 가져오기
					// String email = tokenProvider.getUserEmail(refreshToken);
					// /// 이메일로 권한정보 받아오기
					// List<String> roles = jwtTokenProvider.getRoles(email);
					// /// 토큰 발급
					// String newAccessToken = jwtTokenProvider.createAccessToken(email, roles);
					// /// 헤더에 어세스 토큰 추가
					// jwtTokenProvider.setHeaderAccessToken(response, newAccessToken);
					// /// 컨텍스트에 넣기
					// this.setAuthentication(newAccessToken);
				}
			}
		}
		// filterChain.doFilter(request, response);


		// HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		if (request.getMethod().equals("OPTIONS")) {
			return;
		}
		String jwt = resolveToken(request);
		String requestUri = request.getRequestURI();
		logger.debug("doFilter 들어옴");

		if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
			Authentication authentication = tokenProvider.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri : {}", authentication.getName(), requestUri);
		} else {
			logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestUri);
		}
		chain.doFilter(request, response);
	}


	// Request의 Header에서 AccessToken 값을 가져옵니다. "authorization" : "token'
	public String resolveAccessToken(HttpServletRequest request) {
		if(request.getHeader("authorization") != null )
			return request.getHeader("authorization").substring(7);
		return null;
	}
	// Request의 Header에서 RefreshToken 값을 가져옵니다. "authorization" : "token'
	public String resolveRefreshToken(HttpServletRequest request) {
		if(request.getHeader("refreshToken") != null )
			return request.getHeader("refreshToken").substring(7);
		return null;
	}

	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}
