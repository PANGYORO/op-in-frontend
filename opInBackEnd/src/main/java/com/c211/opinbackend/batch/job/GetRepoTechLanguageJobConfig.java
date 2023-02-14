package com.c211.opinbackend.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.c211.opinbackend.batch.dto.RepoTechLanguageDto;
import com.c211.opinbackend.batch.item.reader.GetRepoTechLanguageReader;
import com.c211.opinbackend.batch.item.writer.GetRepoTechLanguageWriter;
import com.c211.opinbackend.batch.listener.LoggerListener;
import com.c211.opinbackend.batch.step.Action;
import com.c211.opinbackend.persistence.repository.BatchTokenRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.persistence.repository.RepoTechLanguageRepository;
import com.c211.opinbackend.persistence.repository.TechLanguageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GetRepoTechLanguageJobConfig {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final RepoRepository repoRepository;
	private final TechLanguageRepository techLanguageRepository;
	private final RepoTechLanguageRepository repoTechLanguageRepository;
	private final Action action;
	private final BatchTokenRepository batchTokenRepository;

	@Bean
	public Job getRepoTechLanguageJob(Step accessTokenTestStep, Step getAllRepositoryTechLanguageStep, Step batchTokenResetStep) {
		return jobBuilderFactory.get("getRepoTechLanguageJob")
			.incrementer(new RunIdIncrementer())
			.listener(new LoggerListener())
			.start(accessTokenTestStep)
				.on("FAILED").to(batchTokenResetStep).on("*").end()
			.from(accessTokenTestStep)
				.on("*").to(getAllRepositoryTechLanguageStep).on("*").to(batchTokenResetStep).end()
			.build();
	}

	@JobScope
	@Bean
	public Step getAllRepositoryTechLanguageStep() {
		return stepBuilderFactory.get("getAllRepositoryTechLanguageStep")
			.<RepoTechLanguageDto
				, RepoTechLanguageDto>chunk(1)
			.reader(new GetRepoTechLanguageReader(repoRepository, action, batchTokenRepository))
			.writer(new GetRepoTechLanguageWriter(techLanguageRepository, repoTechLanguageRepository))
			.build();
	}
}
