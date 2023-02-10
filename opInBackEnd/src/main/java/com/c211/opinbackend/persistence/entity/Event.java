package com.c211.opinbackend.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
	@GeneratedValue
	@Id
	@Column(name = "EVENT_ID")
	private Long id;
	private String title;
	private String content;
	private LocalDateTime openDate;
	private LocalDateTime endDate;
	private String imgUrl;
	private String link;
}
