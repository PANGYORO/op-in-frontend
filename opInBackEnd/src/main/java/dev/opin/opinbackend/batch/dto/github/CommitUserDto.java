package dev.opin.opinbackend.batch.dto.github;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommitUserDto {
	private String name;
	private String email;
	private LocalDateTime date;
}
