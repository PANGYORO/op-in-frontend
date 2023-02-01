package com.c211.opinbackend.repo.entitiy;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.c211.opinbackend.auth.entity.Member;

import lombok.Getter;

@Entity
@Getter
public class Repository {
	@Id
	@GeneratedValue
	@Column(name = "REPOSITORY_ID")
	private Long id;

	@Embedded
	private TitleContent titleContent;
	private String githubAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

}
