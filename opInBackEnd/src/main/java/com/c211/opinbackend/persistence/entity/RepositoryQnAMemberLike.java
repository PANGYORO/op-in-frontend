package com.c211.opinbackend.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;

@Entity(name = "REPOSITORY_QNA_MEMBER_LIKE")
@Getter
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
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member member;

}
