package com.c211.opinbackend.repo.entitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class HashTag {
	@Id
	@GeneratedValue
	@Column(name = "HASH_TAG_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPOSITORY_POST_ID")
	private RepositoryPost repositoryPost;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPOSITORY_QNA_ID")
	private RepositoryQnA repositoryQnA;

}
