package dev.opin.opinbackend.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.opin.opinbackend.batch.listener.LoggerListener;
import dev.opin.opinbackend.batch.step.Action;
import dev.opin.opinbackend.batch.step.GetRepoContributorTasklet;
import dev.opin.opinbackend.persistence.repository.BatchTokenRepository;
import dev.opin.opinbackend.persistence.repository.GithubContributorRepository;
import dev.opin.opinbackend.persistence.repository.MemberRepository;
import dev.opin.opinbackend.persistence.repository.RepoContributorRepository;
import dev.opin.opinbackend.persistence.repository.RepoRepository;
import dev.opin.opinbackend.persistence.repository.RepositoryGithubContributorRepository;

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
