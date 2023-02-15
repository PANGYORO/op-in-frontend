package com.c211.opinbackend.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.c211.opinbackend.batch.dto.mapper.CommitHistoryMapper;
import com.c211.opinbackend.batch.listener.LoggerListener;
import com.c211.opinbackend.batch.step.Action;
import com.c211.opinbackend.batch.step.GetRepoCommitTasklet;
import com.c211.opinbackend.persistence.repository.BatchTokenRepository;
import com.c211.opinbackend.persistence.repository.CommitHistoryRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class GetRepoCommitJobConfig {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final RepoRepository repoRepository;
	private final CommitHistoryRepository commitHistoryRepository;
	private final CommitHistoryMapper commitHistoryMapper;
	private final BatchTokenRepository batchTokenRepository;
	private final Action action;

	@Bean
	public Job getRepoCommitJob(Step accessTokenTestStep, Step getRepoCommitStep, Step batchTokenResetStep) {
		return jobBuilderFactory.get("getRepoCommitJob")
			.incrementer(new RunIdIncrementer())
			.listener(new LoggerListener())
			.start(accessTokenTestStep)
			.on("FAILED").to(batchTokenResetStep).on("*").end()
			.from(accessTokenTestStep)
			.on("*").to(getRepoCommitStep).on("*").to(batchTokenResetStep).end()
			.build();
	}

	@JobScope
	@Bean
	public Step getRepoCommitStep() {
		return stepBuilderFactory.get("getRepoCommitStep")
			.tasklet(
				new GetRepoCommitTasklet(batchTokenRepository, repoRepository, action, commitHistoryMapper,
					commitHistoryRepository))
			.build();
	}
}
