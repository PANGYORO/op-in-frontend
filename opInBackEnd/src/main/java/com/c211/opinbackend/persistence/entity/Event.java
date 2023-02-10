package com.c211.opinbackend.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sun.istack.NotNull;

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

	@NotNull
	@Column(nullable = false)
	private String title;
	@NotNull
	@Column(nullable = false)
	private String content;
	@NotNull
	@Column(nullable = false)
	private LocalDate openDate;
	private LocalDate endDate;
	private String img;
	private String link;
}
