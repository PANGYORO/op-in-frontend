package dev.opin.opinbackend.auth.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenDto {
	private String type;
	private String accessToken;
	private String refreshToken;

	public TokenDto(String accessToken, String refreshToken) {
		this.type = "Bearer";
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
}
