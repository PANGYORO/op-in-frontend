package com.c211.opinbackend.repo.entitiy;

import javax.persistence.Embeddable;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class TitleContent {
	@NotNull
	private String title;
	@NotNull
	private String content;

}
