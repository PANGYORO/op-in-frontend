package com.c211.opinbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.c211.opinbackend.auth.jwt.JwtExceptionFilter;
import com.c211.opinbackend.auth.jwt.JwtFilter;
import com.c211.opinbackend.auth.jwt.TokenProvider;

@Configuration
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	private TokenProvider tokenProvider;

	public JwtSecurityConfig(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	/**
	 * JWT 필터를 설정합니다.
	 * 순서: JwtExceptionFilter -> JwtFilter -> UsernamePasswordAuthenticationFilter
	 * @param http
	 */
	@Override
	public void configure(HttpSecurity http) {
		JwtFilter jwtFilter = new JwtFilter(tokenProvider);
		JwtExceptionFilter jwtExceptionFilter = new JwtExceptionFilter();
		http.addFilterBefore(jwtExceptionFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterAfter(jwtFilter, JwtExceptionFilter.class);
	}
}

