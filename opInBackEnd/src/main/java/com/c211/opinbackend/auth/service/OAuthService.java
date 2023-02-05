package com.c211.opinbackend.auth.service;

import org.springframework.util.MultiValueMap;

import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.auth.model.TokenDto;
import com.c211.opinbackend.auth.model.response.OAuthAccessTokenResponse;

public interface OAuthService {
	String getRedirectURL();

	TokenDto login(String code);

}
