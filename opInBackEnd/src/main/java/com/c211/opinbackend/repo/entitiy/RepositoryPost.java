package com.c211.opinbackend.repo.entitiy;

import java.time.LocalDateTime;

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
import lombok.Setter;

@Entity
@Getter

public class RepositoryPost {
	@Id
	@GeneratedValue
	@Column(name = "REPOSITORY_POST_ID")
	private Long id;

	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "REPOSITORY_ID")
	private Repository repository;

	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@Embedded
	private TitleContent titleContent;

	private Boolean mergeFL;
	private LocalDateTime  date;
	private Boolean closeState;
}
