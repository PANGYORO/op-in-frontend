package com.c211.opinbackend.event.model.mapper;

import com.c211.opinbackend.event.model.request.RequestUploadEvent;
import com.c211.opinbackend.member.model.response.FileUploadResponse;
import com.c211.opinbackend.persistence.entity.Event;

public class EventMapper {
	public static Event toEntity(RequestUploadEvent eventRequest, FileUploadResponse eventFileResponse) {
		System.out.println(eventRequest.getImg() == null);

		return Event.builder()
			.title(eventRequest.getTitle())
			.content(eventRequest.getContent())
			.openDate(eventRequest.getOpenDate())
			.endDate(eventRequest.getEndDate())
			.img(eventFileResponse.getUrl())
			.link(eventRequest.getLink())
			.build();
	}

	public static Event toEntity(RequestUploadEvent eventRequest) {
		System.out.println(eventRequest.getImg() == null);

		return Event.builder()
			.title(eventRequest.getTitle())
			.content(eventRequest.getContent())
			.openDate(eventRequest.getOpenDate())
			.endDate(eventRequest.getEndDate())
			.img(null)
			.link(eventRequest.getLink())
			.build();
	}
}
