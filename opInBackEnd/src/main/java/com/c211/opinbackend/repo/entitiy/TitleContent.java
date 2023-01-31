package com.c211.opinbackend.repo.entitiy;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class TitleContent {
	@Column(nullable = false)
	@NotNull
	private String title;
	@Column(nullable = false)
	private String content;

}
