package com.c211.opinbackend.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.c211.opinbackend.exception.auth.AuthRuntimeException;

import io.jsonwebtoken.JwtException;

public class JwtExceptionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal (HttpServletRequest req, HttpServletResponse res, FilterChain chain)
		throws ServletException, IOException {
		try {
			chain.doFilter(req, res); // go to 'JwtAuthenticationFilter'
		} catch (AuthRuntimeException ex) {
			setErrorResponse(HttpStatus.UNAUTHORIZED, res, ex);
		}
	}

	public void setErrorResponse(HttpStatus status, HttpServletResponse res, Throwable ex) throws IOException {
		res.setStatus(status.value());
		res.setContentType("application/json; charset=UTF-8");
		res.getWriter().write(ex.toString());
	}
}
