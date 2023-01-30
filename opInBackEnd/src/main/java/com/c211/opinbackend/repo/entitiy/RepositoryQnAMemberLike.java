package com.c211.opinbackend.repo.entitiy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.c211.opinbackend.auth.entity.Member;

@Entity(name = "REPOSITORY_QNA_MEMBER_LIKE")
public class RepositoryQnAMemberLike {

	@Id
	@GeneratedValue
	@Column(name = "REPOSITORY_QNA_MEMBER_LIKE_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "REPOSITORY_QNA_ID")
	private RepositoryQnA repositoryQnA;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

}
