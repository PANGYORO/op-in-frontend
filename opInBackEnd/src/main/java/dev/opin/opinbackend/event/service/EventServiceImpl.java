package dev.opin.opinbackend.event.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.opin.opinbackend.event.model.mapper.EventMapper;
import dev.opin.opinbackend.event.model.request.RequestUpdate;
import dev.opin.opinbackend.event.model.request.RequestUploadEvent;
import dev.opin.opinbackend.exception.event.EventExceptionEnum;
import dev.opin.opinbackend.exception.event.EventExceptionRuntimeException;
import dev.opin.opinbackend.member.model.response.FileUploadResponse;
import dev.opin.opinbackend.persistence.entity.Event;
import dev.opin.opinbackend.persistence.repository.EventRepository;

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

	@Override
	@Transactional
	public Boolean update(RequestUpdate request) {
		Event event = eventRepository.findById(request.getId()).orElseThrow(
			() -> new EventExceptionRuntimeException(EventExceptionEnum.EVENT_NOT_EXIST_EXCEPTION)
		);
		if (request.getTitle().length() != 0 || !Objects.nonNull(request.getTitle())) {
			event.updateTitle(request.getTitle());
		} else if (request.getLink().length() != 0 || !Objects.nonNull(request.getLink())) {
			event.updateLink(event.getLink());
		} else if (request.getContent().length() != 0 || !Objects.nonNull(request.getContent())) {
			event.updateContent(request.getContent());
		} else if (request.getOpenDate().toString().length() != 0) {
			event.updateOpenDate(request.getOpenDate());
		} else if (request.getEndDate().toString().length() != 0) {
			event.updateEndDate(request.getEndDate());

		}
		eventRepository.save(event);
		return true;
	}
}
