package com.c211.opinbackend.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryTechLanguage {
	@Id
	@GeneratedValue
	@Column(name = "REPOSITORY_TECH_LANGUAGE_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TECH_LANGUAGE_ID")
	private TechLanguage techLanguage;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPOSITORY_ID")
	private Repository repository;

	private Long count;

	// @Override
	// public boolean equals(Object obj) {
	// 	if(obj == this) {
	// 		return true;
	// 	}
	// 	if(obj == null || obj instanceof RepositoryTechLanguage) {
	// 		return false;
	// 	}
	// 	RepositoryTechLanguage repoObj = (RepositoryTechLanguage) obj;
	// 	if(repoObj.getId() != this.getId()) {
	// 		return false;
	// 	}
	// 	if(repository.getId() == this.getRepository().getId())
	// }

	// @Override
	// public int hashCode() {
	// 	return id.intValue();
	// }
}
