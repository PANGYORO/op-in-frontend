package dev.opin.opinbackend.event.model.request;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventUpdateRequest {
	private Long id;
	private String name;
	private String content;
	private LocalDate openDate;
	private LocalDate endDate;
	private MultipartFile img;

}
