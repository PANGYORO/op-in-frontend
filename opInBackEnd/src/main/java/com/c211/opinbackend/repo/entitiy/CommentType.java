package com.c211.opinbackend.repo.entitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class CommentType {
	@Id
	@GeneratedValue
	@Column(name = "COMMENT_TYPE")
	private Long id;
	@NotNull
	private String tittle;
}
