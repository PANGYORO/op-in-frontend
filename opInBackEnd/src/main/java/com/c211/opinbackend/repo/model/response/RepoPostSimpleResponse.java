package com.c211.opinbackend.repo.model.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class RepoPostSimpleResponse {
	private Long postId;
	private String authorMemberName;
	private String authorMemberAvatar;
	private LocalDateTime createTime;
	private String title;
	private int likeCount;
	private int commentCount;
}
