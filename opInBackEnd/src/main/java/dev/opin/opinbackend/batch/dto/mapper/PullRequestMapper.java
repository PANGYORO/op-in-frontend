package dev.opin.opinbackend.batch.dto.mapper;

import org.springframework.stereotype.Component;

import dev.opin.opinbackend.batch.dto.github.PullRequestDto;
import dev.opin.opinbackend.persistence.entity.PullRequest;
import dev.opin.opinbackend.persistence.entity.Repository;

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
