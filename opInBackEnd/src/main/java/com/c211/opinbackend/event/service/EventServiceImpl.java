package com.c211.opinbackend.event.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.c211.opinbackend.event.model.mapper.EventMapper;
import com.c211.opinbackend.event.model.request.EventUpdateRequest;
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
		if (eventUpdateRequest.getOpenDate().isBefore(eventUpdateRequest.getEndDate())) {
			throw new EventExceptionRuntimeException(EventExceptionEnum.EVENT_OPEN_DATE_WRONG_EXCEPTION);
		}

		Event event = eventRepository.findById(eventUpdateRequest.getId()).orElseThrow(
			() -> new EventExceptionRuntimeException(EventExceptionEnum.EVENT_NOT_EXIST_EXCEPTION)
		);
		// TODO: 2023-02-10 업데이트 메소드 만들기 
		return true;
	}
}
