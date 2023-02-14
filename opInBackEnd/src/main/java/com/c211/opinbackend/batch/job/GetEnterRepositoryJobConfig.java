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

import com.c211.opinbackend.batch.dto.github.RepositoryDto;
import com.c211.opinbackend.batch.item.reader.GetEnterRepositoryReader;
import com.c211.opinbackend.batch.item.reader.GetMemberRepositoryReader;
import com.c211.opinbackend.batch.item.writer.GetMemberRepositoryWriter;
import com.c211.opinbackend.batch.listener.LoggerListener;
import com.c211.opinbackend.batch.service.RepositoryService;
import com.c211.opinbackend.batch.step.Action;
import com.c211.opinbackend.persistence.repository.EnterpriseRepository;
import com.c211.opinbackend.persistence.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GetEnterRepositoryJobConfig {
	/**
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final RepositoryService repositoryService;
	private final EnterpriseRepository enterpriseRepository;
	private final Action action;
	@Value("${githubToken1}")
	private String githubToken;

	@Bean
	public Job getEnterRepositoryJob(Step getEnterRepositoryStep) {
		return jobBuilderFactory.get("getEnterRepositoryJob")
			.incrementer(new RunIdIncrementer())
			.listener(new LoggerListener())
			.start(getEnterRepositoryStep)
			.build();
	}

	@JobScope
	@Bean
	public Step getEnterRepositoryStep() {
		return stepBuilderFactory.get("getEnterRepositoryStep")
			.<RepositoryDto, RepositoryDto>chunk(1)
			.reader(new GetEnterRepositoryReader(enterpriseRepository, action, githubToken))
			.writer(new GetMemberRepositoryWriter(repositoryService))
			.build();
	}
	*/
}
