package com.c211.opinbackend.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "GITHUB_SYNC")
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GithubSync {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GITHUB_SYNC_ID")
	private Long id;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "LOGIN_NICKNAME")
	@NotNull
	private String loginNickname;
	@Column(name = "REFRESH_TOKEN")
	@NotNull
	private String refreshToken;

	@Column(name = "ACCESS_TOKEN")
	@NotNull
	private String accessToken;

	@OneToOne(mappedBy = "githubSync", fetch = FetchType.LAZY)
	private Member member;

}
