package com.c211.opinbackend.event.model.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestUploadEvent {
	private String title;
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate openDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDate endDate;
	private MultipartFile img;
	private String link;
}
