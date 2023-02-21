package dev.opin.opinbackend.auth.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthAccessTokenRequest {

	@JsonProperty("client_id")
	private String clientId;

	@JsonProperty("client_secret")
	private String clientSecret;

	private String code;

}
