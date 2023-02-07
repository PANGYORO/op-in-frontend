package com.c211.opinbackend.repo.model.response.repoTechLang;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class RepoTechLangDto {
	String title;
	String color;
	Long count;
}
