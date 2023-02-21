package dev.opin.opinbackend.auth.service;

import dev.opin.opinbackend.auth.model.TokenDto;

public interface OAuthService {
	String getRedirectUrl(String redirectUri);

	TokenDto login(String code, String redirectUri);

}
