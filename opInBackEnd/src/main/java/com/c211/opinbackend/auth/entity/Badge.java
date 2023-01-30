package com.c211.opinbackend.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Badge {
	@Id
	@GeneratedValue
	@Column(name = "BADGE_ID")
	private Long id;

	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String image_url;
}
