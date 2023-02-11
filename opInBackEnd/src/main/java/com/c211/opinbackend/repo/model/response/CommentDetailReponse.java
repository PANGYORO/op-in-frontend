package com.c211.opinbackend.repo.model.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CommentDetailReponse {

	private Long id;

	private String memberName;
	private String memberAvatarUrl;

	private String commentContent;

	private LocalDateTime createDate;
	private LocalDateTime updateDate;
}
