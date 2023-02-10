package com.c211.opinbackend.event.model.request;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

public class EventUpdateRequest {

	private String name;
	private String content;
	private LocalDate openDate;
	private LocalDate endDate;
	private MultipartFile img;
	
}
