package com.c211.opinbackend.persistence.entity;

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
public class RepositoryContributor {
	@Id
	@GeneratedValue
	@Column(name = "REPOSITORY_CONTRIBUTE_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPOSITORY_ID")
	private Repository repository;
}
