package com.c211.opinbackend.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "MEMBER")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	@Column(name = "MEMBER_EMAIL")
	@NotNull
	private String email;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "NICKNAME")
	@NotNull
	private String nickname;
	@Column(name = "AVATA_URL")
	private String avatarUrl;
	@Column(name = "GITHUB_SYNC_FL")
	@NotNull
	private boolean githubSyncFl;
	@Enumerated(EnumType.STRING)
	private Role role;
	private String githubToken;
	private boolean githubTokenExpire;
	private String githubId;
	private String githubUserName;

	public Member fetch(String githubToken, String githubUserName) {
		this.githubToken = githubToken;
		this.githubUserName = githubUserName;
		return this;
	}

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	List<Repository> repositoryLists = new ArrayList<>();

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	List<RepositoryFollow> repositoryFollows = new ArrayList<>();

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	List<MemberTechLanguage> techLanguages = new ArrayList<>();

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	List<RepositoryPost> repositoryPosts = new ArrayList<>();
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "authorMember", cascade = CascadeType.ALL)
	List<RepositoryQnA> qnAList = new ArrayList<>();
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	List<MemberBadge> memberBadges = new ArrayList<>();

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	List<RepositoryPostMemberLike> repositoryPostMemberLikes = new ArrayList<>();
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	List<RepositoryQnAMemberLike> repositoryQnAMemberLikes = new ArrayList<>();
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	List<MemberTopic> memberTopics = new ArrayList<>();

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	List<RepositoryContributor> repositoryContributors = new ArrayList<>();

	public void setNickname(String name) {
		this.nickname = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
}
