package com.c211.opinbackend.repo.entitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.c211.opinbackend.auth.entity.TechLanguage;

import lombok.Getter;

@Entity
@Getter
public class RepositoryTechLanguage {
	@Id
	@GeneratedValue
	@Column(name="REPOSITORY_TECH_LANGUAGE_ID")
	private Long id;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TECH_LANGUAGE_ID")
	private TechLanguage techLanguage;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPOSITORY_ID")
	private Repository repository;

}
