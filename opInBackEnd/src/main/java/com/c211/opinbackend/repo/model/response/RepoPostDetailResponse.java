package com.c211.opinbackend.repo.model.response;

import java.util.List;

import com.c211.opinbackend.repo.model.dto.CommentDto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class RepoPostDetailResponse extends RepoPostSimpleResponse {
	private List<CommentDto> commentList;
	private String content;

}
