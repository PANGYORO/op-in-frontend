package com.c211.opinbackend.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.c211.opinbackend.batch.listener.LoggerListener;
import com.c211.opinbackend.batch.service.RepositoryService;
import com.c211.opinbackend.batch.step.Action;
import com.c211.opinbackend.batch.step.GetRepoContributorTasklet;
import com.c211.opinbackend.persistence.repository.BatchTokenRepository;
import com.c211.opinbackend.persistence.repository.GithubContributorRepository;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.persistence.repository.RepoContributorRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.persistence.repository.RepositoryGithubContributorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GetRepoContributorJobConfig {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final RepoRepository repoRepository;
	private final MemberRepository memberRepository;
	private final RepoContributorRepository repoContributorRepository;
	private final Action action;
	private final BatchTokenRepository batchTokenRepository;
	private final GithubContributorRepository githubContributorRepository;
	private final RepositoryGithubContributorRepository repositoryGithubContributorRepository;

	@Bean
	public Job getRepoContributorJob(Step accessTokenTestStep, Step getRepoContributorStep, Step batchTokenResetStep) {
		return jobBuilderFactory.get("getRepoContributorJob")
			.incrementer(new RunIdIncrementer())
			.listener(new LoggerListener())
			.start(accessTokenTestStep)
			.on("FAILED").to(batchTokenResetStep).on("*").end()
			.from(accessTokenTestStep)
			.on("*").to(getRepoContributorStep).on("*").to(batchTokenResetStep).end()
			.build();
	}

	@JobScope
	@Bean
	public Step getRepoContributorStep() {
		return stepBuilderFactory.get("getRepoContributorStep")
			.tasklet(
				new GetRepoContributorTasklet(batchTokenRepository, repoRepository, action, memberRepository,
					repoContributorRepository, githubContributorRepository, repositoryGithubContributorRepository))
			.build();
	}
}
