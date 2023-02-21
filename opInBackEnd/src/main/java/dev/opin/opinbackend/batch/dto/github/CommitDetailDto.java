package dev.opin.opinbackend.batch.dto.github;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommitDetailDto {
	private CommitUserDto author;
	private CommitUserDto committer;
	private String message;
}
