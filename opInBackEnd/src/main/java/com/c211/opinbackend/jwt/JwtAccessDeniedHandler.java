package com.c211.opinbackend.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.c211.opinbackend.exception.auth.AuthExceptionEnum;
import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// response.sendError(HttpServletResponse.SC_FORBIDDEN, MemberExceptionEnum.MEMBER_ACCESS_EXCEPTION.getErrorMessage());
		throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_WRONG_EXCEPTION);
	}

}

