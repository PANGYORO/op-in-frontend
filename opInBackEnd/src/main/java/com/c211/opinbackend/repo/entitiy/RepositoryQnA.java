package com.c211.opinbackend.repo.entitiy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.c211.opinbackend.auth.entity.Member;

import lombok.Getter;

@Entity
@Getter
public class RepositoryQnA {
	@Id
	@GeneratedValue
	@Column(name = "REPOSITORY_QNA_ID")
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "REPOSITORY_ID")
	private Repository repository;

	@Embedded
	private TitleContent titleContent;
	private String comment;


}
