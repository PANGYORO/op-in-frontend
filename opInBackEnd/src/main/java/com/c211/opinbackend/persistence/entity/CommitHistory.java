package com.c211.opinbackend.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Entity(name = "COMMIT_HISTORY")
@Getter
public class CommitHistory {
	@Id
	@GeneratedValue
	@Column(name = "COMMIT_HISTORY_ID")
	private Long id;

	@JoinColumn(name = "REPOSITORY_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private Repository repository;
	private String date;

}
