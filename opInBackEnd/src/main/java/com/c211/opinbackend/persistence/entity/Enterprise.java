package com.c211.opinbackend.persistence.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sun.istack.NotNull;

public class Enterprise {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String title;
}
