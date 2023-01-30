package com.c211.opinbackend.repo.entitiy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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

	@OneToMany(mappedBy = "repository", fetch = FetchType.LAZY)
	private List<CommitHistory> commitHistory = new ArrayList<>();

}
