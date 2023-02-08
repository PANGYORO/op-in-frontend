package com.c211.opinbackend.repo.model.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RepoQnAResponse {
	private String authorMember;
	private String authorAvatar;
	private LocalDateTime createTime;
	private String content;
	private List<QnAComment> qnACommentList = new ArrayList<>();
}
