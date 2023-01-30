package com.c211.opinbackend.repo.entitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.c211.opinbackend.auth.entity.Member;

import lombok.Getter;

@Entity
@Getter
public class Comment {
	@Id
	@GeneratedValue
	@Column(name = "COMMENT_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@OneToOne
	@JoinColumn(name = "COMMENT_TYPE_ID")
	private Type type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPOSITORY_QNA_ID")
	private RepositoryQnA repositoryQnA;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPOSITORY_POST_ID")
	private RepositoryPost post;
	private String content;

}
