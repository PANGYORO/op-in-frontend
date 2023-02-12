package com.c211.opinbackend.member.model.dto;

import com.c211.opinbackend.persistence.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDto {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long id;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String email;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String password;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String nickname;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String avataUrl;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean githubSyncFl;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Role role;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String githubToken;
	private String githubId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String githubUserName;
}
