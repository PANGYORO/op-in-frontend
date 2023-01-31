package com.c211.opinbackend.auth.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.c211.opinbackend.repo.entitiy.Issue;

import lombok.Getter;

@Entity
@Getter
public class IssueContributor {
	@Id
	@GeneratedValue
	@Column(name = "ISSUE_CONTRIBUTOR_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ISSUE_ID")
	private Issue issue;
}
