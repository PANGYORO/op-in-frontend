package dev.opin.opinbackend.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryGithubContributor {

	@Id
	@GeneratedValue
	@Column(name = "REPOSITORY_GITHUB_CONTRIBUTOR_ID")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GITHUB_CONTRIBUTOR_ID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private GithubContributor githubContributor;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPOSITORY_ID")
	private Repository repository;

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public void setGithubContributor(GithubContributor githubContributor) {
		this.githubContributor = githubContributor;
	}
}
