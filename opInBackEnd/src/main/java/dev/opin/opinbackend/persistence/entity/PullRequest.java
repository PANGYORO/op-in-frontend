package dev.opin.opinbackend.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PullRequest {
	@Id
	@Column(name = "PULL_REQUEST_ID")
	private Long id;

	private String htmUrl;

	private Long number;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "REPOSITORY_ID")
	private Repository repository;

	private String state;

	private String title;

	private String userId;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime closedAt;
	private LocalDateTime mergedAt;


	public void setRepository(Repository repo) {
		this.repository = repo;
	}

}
