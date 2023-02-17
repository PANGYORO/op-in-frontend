package com.c211.opinbackend.member.model.dto;

import com.c211.opinbackend.persistence.entity.Repository;

public class SimilarDto implements Comparable<SimilarDto> {
	public double similarity;
	public Repository repo;

	public SimilarDto(Double similarity, Repository repo) {
		this.similarity = similarity;
		this.repo = repo;
	}

	@Override
	public int compareTo(SimilarDto o) {
		return Double.compare(this.similarity,o.similarity);
	}
}
