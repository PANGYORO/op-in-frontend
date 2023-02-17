package com.c211.opinbackend.batch.dto.github;

import java.time.LocalDateTime;

import javax.persistence.Id;

import org.hibernate.annotations.Comment;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PullRequestDto {

	private Long id;

	@JsonProperty("html_url")
	private String htmlUrl;

	@Comment("PULL REQUEST의 Number를 의미합니다.")
	private Long number;

	@Comment("PULL REQUEST의 State로 open, closed를 알 수 있습니다.")
	private String state;

	private String title;

	private UserDto user;

	@JsonProperty("created_at")
	private LocalDateTime createdAt;
	@JsonProperty("updated_at")
	private LocalDateTime updatedAt;
	@JsonProperty("closed_at")
	private LocalDateTime closedAt;
	@JsonProperty("merged_at")
	private LocalDateTime mergedAt;


}
