package com.c211.opinbackend.member.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUploadResponse {

	String fileName;
	String url;

	public FileUploadResponse(String fileName, String url) {
		this.fileName = fileName;
		this.url = url;
	}
}
