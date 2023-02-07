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
public class RepositoryTopic {
	@Id
	@GeneratedValue
	@Column(name = "REPOSITORY_TOPIC_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TOPIC_ID")
	private Topic topic;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "REPOSITORY_ID")
	private Repository repository;

}
