package dev.opin.opinbackend.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.lang.Nullable;

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

	public void updateTitle(String title) {
		this.title = title;
	}

	@NotNull
	@Column(nullable = false)
	private LocalDate openDate;

	private LocalDate endDate;
	@Nullable
	private String img;
	private String link;

	public void updateContent(String content) {
		this.content = content;
	}

	public void updateOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public void updateEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void updateImg(@Nullable String img) {
		this.img = img;
	}

	public void updateLink(String link) {
		this.link = link;
	}
}
