package com.c211.opinbackend.auth.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class OAuthAccessTokenResponse {

	@JsonProperty("access_token")
	String accessToken;

	String scope;
	@JsonProperty("token_type")
	String tokenType;

	@JsonProperty("redirect_uri")
	String redirectUri;
	@Builder
	public OAuthAccessTokenResponse(String accessToken, String scope, String tokenType, String redirectUri) {
		this.accessToken = accessToken;
		this.tokenType = tokenType;
		this.scope = scope;
		this.redirectUri = redirectUri;
	}
}
