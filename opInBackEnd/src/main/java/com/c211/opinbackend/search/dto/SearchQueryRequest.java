package com.c211.opinbackend.search.dto;

import lombok.Data;

@Data
public class SearchQueryRequest {
	String query;
	int page;
	int size;
}
