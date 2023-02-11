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
public class MemberTechLanguage {
	@Id
	@GeneratedValue
	@Column(name = "MEMBER_TECH_LANGUAGE_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TECH_LANGUAGE_ID")
	private TechLanguage techLanguage;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	private String color;

	public void setMember(Member member) {
		this.member = member;
	}

	public void setTechLanguage(TechLanguage techLanguage) {
		this.techLanguage = techLanguage;
	}

}
