package dev.opin.opinbackend.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GithubContributor {

	@Id
	@GeneratedValue
	@Column(name = "GITHUB_CONTRIBUTOR_ID")
	private Long id;

	private String authorId;

	private String nickname;

	private String avatarUrl;

	private String githubUrl;

}
