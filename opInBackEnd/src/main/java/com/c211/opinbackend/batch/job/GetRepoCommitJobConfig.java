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

import com.c211.opinbackend.batch.dto.github.CommitDto;
import com.c211.opinbackend.batch.dto.mapper.CommitHistoryMapper;
import com.c211.opinbackend.batch.item.reader.GetRepoCommitReader;
import com.c211.opinbackend.batch.item.writer.GetRepoCommitWriter;
import com.c211.opinbackend.batch.listener.LoggerListener;
import com.c211.opinbackend.batch.step.Action;
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
	private final Action action;

	@Value("${githubToken2}")
	private String githubToken;

	@Bean
	public Job getRepoCommitJob(Step getRepoCommitStep) {
		return jobBuilderFactory.get("getRepoCommitJob")
			.incrementer(new RunIdIncrementer())
			.listener(new LoggerListener())
			.start(getRepoCommitStep)
			.build();
	}

	@JobScope
	@Bean
	public Step getRepoCommitStep() {
		return stepBuilderFactory.get("getRepoCommitStep")
			.<CommitDto
				, CommitDto>chunk(1)
			.reader(new GetRepoCommitReader(repoRepository, action, githubToken))
			.writer(new GetRepoCommitWriter(commitHistoryRepository, commitHistoryMapper))
			.build();
	}
}
