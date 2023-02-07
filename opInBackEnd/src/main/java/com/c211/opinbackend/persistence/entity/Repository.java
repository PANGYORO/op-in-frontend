package com.c211.opinbackend.persistence.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@Builder()
@NoArgsConstructor
public class Repository {
	@Id
	@GeneratedValue
	@Column(name = "REPOSITORY_ID")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@NotNull
	private String name;
	@NotNull
	private String githubAddress;

	private String description;
	@NotNull
	private String fullName;
	@NotNull
	private String htmlUrl;
	@NotNull
	private Boolean secret;
	@NotNull
	private Boolean fork;
	@NotNull
	private LocalDateTime createdAt;
	@NotNull
	private LocalDateTime updatedAt;
	@NotNull
	private LocalDateTime pushedAt;
	private Long size;
	private Long stargazersCount;
	private Long watchersCount;
	@NotNull
	private Boolean archived;
	@NotNull
	private Boolean disabled;
	@NotNull
	private Long forks;
	@NotNull
	private Long watchers;

	@OneToMany(mappedBy = "repository")
	List<RepositoryTechLanguage> repositoryTechLanguages = new ArrayList<>();
	@OneToMany(mappedBy = "repository")
	List<Issue> issueList = new ArrayList<>();

	@OneToMany(mappedBy = "repository")
	List<RepositoryPost> repositoryPostList = new ArrayList<>();

	@OneToMany(mappedBy = "repository")
	List<RepositoryQnA> repositoryQnAList = new ArrayList<>();

	@OneToMany(mappedBy = "repository")
	List<RepositoryContributor> repositoryContributorList = new ArrayList<>();

	@OneToMany(mappedBy = "repository")
	List<RepositoryTopic> topicList = new ArrayList<>();

}
