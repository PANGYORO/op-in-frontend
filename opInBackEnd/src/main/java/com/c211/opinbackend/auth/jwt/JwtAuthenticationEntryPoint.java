package com.c211.opinbackend.auth.jwt;

import static com.c211.opinbackend.exception.auth.AuthExceptionEnum.*;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.c211.opinbackend.exception.auth.AuthExceptionEnum;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	/**
	 * 인증 실패시 동작하는 스프링 시큐리티 설정파일
	 * status 401
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws
		IOException {
		response.setStatus(AUTH_AUTHORIZATION_EXCEPTION.getHttpStatus().value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		try (OutputStream os = response.getOutputStream()) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(os,
				AuthExceptionEnum.convertMap(AUTH_AUTHORIZATION_EXCEPTION));
			os.flush();
		}
	}

}
