package com.c211.opinbackend.auth.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthAccessTokenResponse {

	@JsonProperty("access_token")
	String accessToken;
	@JsonProperty("token_type")
	String tokenType;

	String scope;
}
