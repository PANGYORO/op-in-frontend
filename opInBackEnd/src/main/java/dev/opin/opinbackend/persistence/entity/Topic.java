package dev.opin.opinbackend.persistence.entity;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
	@Id
	@GeneratedValue
	@Column(name = "TOPIC_ID")
	private Long id;
	@Column(nullable = false)
	private String title;
}
