package com.c211.opinbackend.auth.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MailDto {
	private String fromAddress;
	private String toAddress;
	private String Subject;
	private String content;
	private boolean isUseHtml;
}
