package com.c211.opinbackend.repo.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RepositoryTitleResponse {

	private long id;

	private String title;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String html;
}
