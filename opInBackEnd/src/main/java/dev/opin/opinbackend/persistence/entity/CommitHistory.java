package dev.opin.opinbackend.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "COMMIT_HISTORY")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommitHistory {
	@Id
	private String sha;

	@JoinColumn(name = "REPOSITORY_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private Repository repository;

	private LocalDateTime date;

	@Lob
	private String message;

	private String authorId;
	private String authorName;
	private String authorAvatarUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_sha")
	private CommitHistory parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL)
	private List<CommitHistory> children;
}
