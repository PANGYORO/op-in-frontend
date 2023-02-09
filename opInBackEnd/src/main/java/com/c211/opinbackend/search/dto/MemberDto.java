package com.c211.opinbackend.search.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MemberDto {
	private Long id;
	private String nickname;

	@JsonProperty("user_img")
	private String avatarUrl;

	private boolean follow;
}
