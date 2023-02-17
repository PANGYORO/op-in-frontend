package com.c211.opinbackend.repo.entitiy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.entity.RepositoryContributor;

import lombok.Getter;

@Entity
@Getter
public class Repository {
	@Id
	@GeneratedValue
	@Column(name = "REPOSITORY_ID")
	private Long id;

	@Embedded
	private TitleContent titleContent;
	private String githubAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

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
