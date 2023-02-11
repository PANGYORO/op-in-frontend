package com.c211.opinbackend.repo.model.dto;

import com.c211.opinbackend.persistence.entity.CommentType;
import com.c211.opinbackend.persistence.entity.Member;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

// TODO: 2023/02/11 req res 나중에 변형해야하는데 만약 맴버 아이디만 원하면 네스티드하게 주는방법밖에 없습니다
@Builder
@Getter
public class CommentDto {
	private Long id;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Member member;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long repositoryId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long repositoryQnA;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long repositoryPostId;
	private String content;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private CommentType commentType;
}
