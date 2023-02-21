package dev.opin.opinbackend.event.service;

import java.util.List;

import dev.opin.opinbackend.event.model.request.RequestUpdate;
import dev.opin.opinbackend.event.model.request.RequestUploadEvent;
import dev.opin.opinbackend.member.model.response.FileUploadResponse;
import dev.opin.opinbackend.persistence.entity.Event;

public interface EventService {
	Event createEvent(RequestUploadEvent requestUploadEvent, FileUploadResponse eventFileResponse);

	List<Event> getALlEvent();

	Boolean delete(Long eventId);

	Boolean update(RequestUpdate requestUploadEvent);
}
