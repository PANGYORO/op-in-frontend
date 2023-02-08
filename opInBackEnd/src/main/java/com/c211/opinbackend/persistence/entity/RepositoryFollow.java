package com.c211.opinbackend.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Entity
@Getter
public class RepositoryFollow {
	@GeneratedValue
	@Id
	@Column(name = "REPOSITORY_FOLLOW_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "REPOSITORY_ID")
	private Repository repository;

}

