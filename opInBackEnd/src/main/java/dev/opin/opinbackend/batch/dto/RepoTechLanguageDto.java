package dev.opin.opinbackend.batch.dto;


import dev.opin.opinbackend.persistence.entity.Repository;

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
