package com.c211.opinbackend.repo.model.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PageResponsePost {
	private int totalPages;
	private Long totalElements;
	private int number;
	private int size;

	private List<RepoPostSimpleResponse> repoPostSimpleResponseList;
}
