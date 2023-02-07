package com.c211.opinbackend.persistence.entity;

import javax.persistence.Embeddable;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class TitleContent {
	@NotNull
	private String title;
	@NotNull
	private String content;

}
