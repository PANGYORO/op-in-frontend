package com.c211.opinbackend.event.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.c211.opinbackend.event.model.mapper.EventMapper;
import com.c211.opinbackend.event.model.request.EventUpdateRequest;
import com.c211.opinbackend.event.model.request.RequestUploadEvent;
import com.c211.opinbackend.member.model.response.FileUploadResponse;
import com.c211.opinbackend.persistence.entity.Event;
import com.c211.opinbackend.persistence.repository.EventRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

	private final EventRepository eventRepository;

	@Override
	@Transactional
	public Event createEvent(RequestUploadEvent requestUploadEvent, FileUploadResponse eventFileResponse) {
		if (eventFileResponse == null) {
			Event event = EventMapper.toEntity(requestUploadEvent);
			return eventRepository.save(event);
		} else {
			Event event = EventMapper.toEntity(requestUploadEvent, eventFileResponse);
			return eventRepository.save(event);
		}
	}

	@Override
	public List<Event> getALlEvent() {
		return eventRepository.findAll();
	}

	@Override
	public Boolean updateEvent(EventUpdateRequest eventUpdateRequest) {
		return null;
	}
}
