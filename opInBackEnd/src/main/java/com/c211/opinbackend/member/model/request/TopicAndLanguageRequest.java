package com.c211.opinbackend.member.model.request;

import java.util.List;

import lombok.Getter;

@Getter
public class TopicAndLanguageRequest {

	private String email;
	private List<String> topic;
	private List<String> lan;
}
