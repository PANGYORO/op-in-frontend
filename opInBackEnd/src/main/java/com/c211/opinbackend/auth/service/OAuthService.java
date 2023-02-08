package com.c211.opinbackend.auth.service;

import com.c211.opinbackend.auth.model.TokenDto;

public interface OAuthService {
	String getRedirectUrl(String redirectUri);

	TokenDto login(String code, String redirectUri);

}
