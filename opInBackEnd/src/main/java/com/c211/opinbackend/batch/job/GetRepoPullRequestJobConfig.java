package com.c211.opinbackend.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.c211.opinbackend.batch.dto.mapper.PullRequestMapper;
import com.c211.opinbackend.batch.item.reader.GetRepoPullRequestReader;
import com.c211.opinbackend.batch.item.writer.GetRepoPullRequestWriter;
import com.c211.opinbackend.batch.listener.LoggerListener;
import com.c211.opinbackend.persistence.entity.PullRequest;
import com.c211.opinbackend.persistence.repository.PullRequestRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GetRepoPullRequestJobConfig {
/*
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final RepoRepository repoRepository;
	private final PullRequestMapper pullRequestMapper;
	private final PullRequestRepository pullRequestRepository;

	@Bean
	public Job getRepoPullRequestJob(Step getRepoPullRequestStep) {
		return jobBuilderFactory.get("getRepoPullRequestJob")
			.incrementer(new RunIdIncrementer())
			.listener(new LoggerListener())
			.start(getRepoPullRequestStep)
			.build();
	}

	@JobScope
	@Bean
	public Step getRepoPullRequestStep() {
		return stepBuilderFactory.get("getRepoPullRequestStep")
			.<PullRequest
				, PullRequest>chunk(1)
			.reader(new GetRepoPullRequestReader(repoRepository, pullRequestMapper))
			.writer(new GetRepoPullRequestWriter(pullRequestRepository))
			.build();
	}

 */
}
