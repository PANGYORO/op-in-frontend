package com.c211.opinbackend.repo.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QnAComment {
	private String commentMemberAvatar;
	private String commentMemberName;
	private String name;
}
