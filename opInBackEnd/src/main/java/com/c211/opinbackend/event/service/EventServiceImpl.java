package com.c211.opinbackend.event.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.c211.opinbackend.event.model.mapper.EventMapper;
import com.c211.opinbackend.event.model.request.RequestUploadEvent;
import com.c211.opinbackend.exception.event.EventExceptionEnum;
import com.c211.opinbackend.exception.event.EventExceptionRuntimeException;
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
		Event event;
		if (eventFileResponse == null) {
			event = EventMapper.toEntity(requestUploadEvent);
		} else {
			event = EventMapper.toEntity(requestUploadEvent, eventFileResponse);
		}
		return eventRepository.save(event);
	}

	@Override
	public List<Event> getALlEvent() {
		return eventRepository.findAll();
	}

	@Override
	public Boolean delete(Long eventId) {
		Event event = eventRepository.findById(eventId).orElseThrow(
			() -> new EventExceptionRuntimeException(EventExceptionEnum.EVENT_NOT_EXIST_EXCEPTION)
		);
		try {
			eventRepository.delete(event);
		} catch (Exception exception) {
			return false;
		}
		return true;
	}
}
