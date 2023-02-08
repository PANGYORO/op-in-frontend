package com.c211.opinbackend.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Topic {
	@Id
	@GeneratedValue
	@Column(name = "TOPIC_ID")
	private Long id;
	@Column(nullable = false)
	private String title;
}
