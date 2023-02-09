package com.c211.opinbackend.auth.jwt;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import com.c211.opinbackend.exception.auth.AuthExceptionEnum;
import com.c211.opinbackend.exception.auth.AuthRuntimeException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtExceptionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest req,
		HttpServletResponse res, FilterChain chain)
		throws IOException {
		try {
			chain.doFilter(req, res);
		} catch (Exception ex) {
			if (ex instanceof AuthRuntimeException) {
				setErrorResponse(res, (AuthRuntimeException)ex);
			}
		}
	}

	/**
	 * AuthRuntimeException 이 발생하게 되면 HttpServletResponse 를 사용해
	 * 에러 메시지를 클라이언트로 전달합니다.
	 *
	 * @param res
	 * @param exception
	 * @throws IOException
	 */
	public void setErrorResponse(HttpServletResponse res, AuthRuntimeException exception) throws IOException {
		res.setContentType(MediaType.APPLICATION_JSON_VALUE);
		res.setStatus(exception.getErrorEnum().getHttpStatus().value());
		try (OutputStream os = res.getOutputStream()) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(os, AuthExceptionEnum.convertMap(exception.getErrorEnum()));
			os.flush();
		}
	}
}
