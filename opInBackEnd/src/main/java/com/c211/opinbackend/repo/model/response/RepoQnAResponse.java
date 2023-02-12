package com.c211.opinbackend.repo.model.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RepoQnAResponse {
	private Long qnaId;
	private String authorMember;
	private String authorAvatar;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime createTime;
	private String content;
	private List<CommentSimpleResponse> qnACommentList;
}
