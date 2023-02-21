package dev.opin.opinbackend.exception.event;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EventExceptionEntity {

	private HttpStatus httpCode;
	private String errorMessage;
}
