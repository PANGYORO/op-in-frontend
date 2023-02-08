package com.c211.opinbackend.batch.dto.opin;

import com.c211.opinbackend.persistence.entity.TechLanguage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TechLanguageDto {
	Long id;
	String title;
	String color;

	public static TechLanguage toEntity(TechLanguageDto dto) {
		return new TechLanguage().builder()
			.id(dto.getId())
			.title(dto.getTitle())
			.color(dto.getColor())
			.build();
	}
}
