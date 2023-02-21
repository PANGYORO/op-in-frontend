package dev.opin.opinbackend.repo.model.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class RepoPostSimpleResponse {
	private Long id;

	private String title;

	private String authorMemberName;
	private String authorMemberAvatar;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime date;

	private int likeCount;
	private int commentCount;

	private boolean mergeFl;
	private boolean closeState;
}
