package com.c211.opinbackend.batch.dto.mapper;

import org.springframework.stereotype.Component;

import com.c211.opinbackend.batch.dto.github.PullRequestDto;
import com.c211.opinbackend.persistence.entity.PullRequest;
import com.c211.opinbackend.persistence.entity.Repository;

@Component
public class PullRequestMapper {
	public static PullRequest toPullRequest(PullRequestDto dto, Repository repository) {
		return PullRequest.builder()
			.id(dto.getId())
			.state(dto.getState())
			.title(dto.getTitle())
			.number(dto.getNumber())
			.htmUrl(dto.getHtmlUrl())
			.userId(dto.getUser().getId().toString())
			.updatedAt(dto.getUpdatedAt())
			.createdAt(dto.getCreatedAt())
			.mergedAt(dto.getMergedAt())
			.closedAt(dto.getClosedAt())
			.repository(repository)
			.build();
	}
}
