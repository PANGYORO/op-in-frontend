package com.c211.opinbackend.event.service;

import com.c211.opinbackend.event.model.request.RequestUploadEvent;
import com.c211.opinbackend.member.model.response.FileUploadResponse;
import com.c211.opinbackend.persistence.entity.Event;

public interface EventService {
	Event createEvent(RequestUploadEvent requestUploadEvent, FileUploadResponse eventFileResponse);
}
