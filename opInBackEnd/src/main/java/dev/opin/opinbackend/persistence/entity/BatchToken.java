package dev.opin.opinbackend.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "BATCH_TOKEN")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchToken {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String accessToken;
}
