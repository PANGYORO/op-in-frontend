package com.c211.opinbackend.event.controller;

import java.io.IOException;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c211.opinbackend.event.model.request.RequestUploadEvent;
import com.c211.opinbackend.event.service.EventService;
import com.c211.opinbackend.exception.event.EventExceptionEnum;
import com.c211.opinbackend.exception.event.EventExceptionRuntimeException;
import com.c211.opinbackend.member.model.response.FileUploadResponse;
import com.c211.opinbackend.member.service.S3FileUploadService;
import com.c211.opinbackend.persistence.entity.Event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/event")
@AllArgsConstructor
@Slf4j
public class EventController {
	private final EventService eventService;
	private final S3FileUploadService s3FileUploadService;

	@PostMapping
	public ResponseEntity<?> createEvent(@ModelAttribute RequestUploadEvent request) throws IOException {
		// 여기서 입력 널인친구들 검사
		// title, content, openDate 널이면안된다. endDate 는 널일수 있습니다.
		Event event = null;
		if (request.getTitle().isBlank() || request.getTitle().length() == 0) {
			throw new EventExceptionRuntimeException(EventExceptionEnum.EVENT_TITLE_NULL_EXCEPTION);
		} else if (request.getContent().isBlank() || request.getContent().length() == 0) {
			throw new EventExceptionRuntimeException(EventExceptionEnum.EVENT_CONTENT_NULL_EXCEPTION);
		} else if (Objects.isNull(request.getOpenDate()) || request.getOpenDate().toString().isBlank()) {
			throw new EventExceptionRuntimeException(EventExceptionEnum.EVENT_OPEN_DATE_NULL_EXCEPTION);
		} else if (!Objects.isNull(request.getEndDate())
			&& request.getEndDate().isBefore(request.getOpenDate())) {
			throw new EventExceptionRuntimeException(EventExceptionEnum.EVENT_OPEN_DATE_WRONG_EXCEPTION);
		}
		if (!request.getImg().equals(null)) {
			log.info("여기입니다", request.getImg().getOriginalFilename().toString());

			FileUploadResponse eventFileResponse = s3FileUploadService.upload(request.getImg(), "event");
			event = eventService.createEvent(request, eventFileResponse);
		} else {
			event = eventService.createEvent(request, null);
		}
		return ResponseEntity.ok().body(event);
	}

}
