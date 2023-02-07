package com.c211.opinbackend.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;

@Entity
@Getter
public class Type {
	@Id
	@GeneratedValue
	@Column(name = "COMMENT_ID")
	private Long id;

	@OneToOne(mappedBy = "type")
	private Comment comment;
	@Column(nullable = false)
	private String title;
}
