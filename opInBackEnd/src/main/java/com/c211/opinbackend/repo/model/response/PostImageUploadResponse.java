package com.c211.opinbackend.repo.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class PostImageUploadResponse {
	String fileName;
	String url;
}
