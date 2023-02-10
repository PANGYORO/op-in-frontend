package com.c211.opinbackend.event.service;

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

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

	private final EventRepository eventRepository;

	@Override
	@Transactional
	public Event createEvent(RequestUploadEvent requestUploadEvent, FileUploadResponse eventFileResponse) {
		// TODO: 2023-02-10 매퍼로 변환후 저장
		Event event = EventMapper.toEntity(requestUploadEvent, eventFileResponse);
		Event save = eventRepository.save(event);
		if (save == null) {
			throw new EventExceptionRuntimeException(EventExceptionEnum.EVENT_SAVE_FAIL_EXCEPTION);
		}
		return save;
	}
}
