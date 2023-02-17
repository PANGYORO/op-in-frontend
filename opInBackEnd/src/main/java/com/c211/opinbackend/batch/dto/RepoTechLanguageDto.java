package com.c211.opinbackend.batch.dto;


import java.util.List;

import com.c211.opinbackend.persistence.entity.Repository;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class RepoTechLanguageDto {

	private Repository repository;

	private String language;
}
