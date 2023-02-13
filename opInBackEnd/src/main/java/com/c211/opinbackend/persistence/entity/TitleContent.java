package com.c211.opinbackend.persistence.entity;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

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
	@Lob
	private String content;

	public void changeTitle(String newTitle) {
		this.title = newTitle;
	}

	public void changeContent(String newContent) {
		this.content = newContent;
	}

}
