package dev.opin.opinbackend.batch.dto.github;

import java.util.List;

import dev.opin.opinbackend.persistence.entity.Repository;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommitDto {

	private Repository repo;
	private String sha;
	private CommitDetailDto commit;
	private String url;
	@JsonProperty("html_url")
	private String htmlUrl;

	private UserDto author;
	private UserDto committer;

	private List<CommitDto> parents;
}
