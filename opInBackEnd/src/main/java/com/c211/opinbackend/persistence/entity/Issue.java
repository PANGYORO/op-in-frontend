package com.c211.opinbackend.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import lombok.Getter;

@Entity
@Getter
public class Issue {
	@Id
	@GeneratedValue
	@Column(name = "ISSUE_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPOSITORY_ID")
	private Repository repository;

	@Column(nullable = false)
	private String title;
	private String content;
	@Column(nullable = false)
	@NotNull
	private Boolean doneFl;
}
