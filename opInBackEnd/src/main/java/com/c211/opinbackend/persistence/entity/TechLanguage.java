package com.c211.opinbackend.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder()
@NoArgsConstructor
@AllArgsConstructor
public class TechLanguage {
	@Id
	@GeneratedValue
	@Column(name = "TECH_LANGUAGE_ID")
	private Long id;
	@NotNull
	private String title;

	private String color;
}
