package com.c211.opinbackend.event.service;

import java.util.List;

import com.c211.opinbackend.event.model.request.EventUpdateRequest;
import com.c211.opinbackend.event.model.request.RequestUploadEvent;
import com.c211.opinbackend.member.model.response.FileUploadResponse;
import com.c211.opinbackend.persistence.entity.Event;

public interface EventService {
	Event createEvent(RequestUploadEvent requestUploadEvent, FileUploadResponse eventFileResponse);

	List<Event> getALlEvent();

	Boolean updateEvent(EventUpdateRequest eventUpdateRequest);
}
