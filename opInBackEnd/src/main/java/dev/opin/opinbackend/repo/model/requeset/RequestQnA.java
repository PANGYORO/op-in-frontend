package dev.opin.opinbackend.repo.model.requeset;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RequestQnA {
	private String comment;
	private Long repoId;
}
