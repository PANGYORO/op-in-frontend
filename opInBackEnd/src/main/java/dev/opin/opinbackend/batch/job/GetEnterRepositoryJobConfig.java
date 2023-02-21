package dev.opin.opinbackend.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.opin.opinbackend.batch.dto.github.RepositoryDto;
import dev.opin.opinbackend.batch.item.reader.AccessTokenTestReader;
import dev.opin.opinbackend.batch.item.reader.GetEnterRepositoryReader;
import dev.opin.opinbackend.batch.item.writer.AccessTokenTestWriter;
import dev.opin.opinbackend.batch.item.writer.GetMemberRepositoryWriter;
import dev.opin.opinbackend.batch.listener.LoggerListener;
import dev.opin.opinbackend.batch.service.BatchTokenService;
import dev.opin.opinbackend.batch.service.RepositoryService;
import dev.opin.opinbackend.batch.step.Action;
import dev.opin.opinbackend.batch.step.BatchTokenResetTasklet;
import dev.opin.opinbackend.persistence.repository.BatchTokenRepository;
import dev.opin.opinbackend.persistence.repository.EnterpriseRepository;
import dev.opin.opinbackend.persistence.repository.MemberRepository;
import dev.opin.opinbackend.persistence.repository.RepoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GetEnterRepositoryJobConfig {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final RepositoryService repositoryService;
	private final EnterpriseRepository enterpriseRepository;
	private final MemberRepository memberRepository;
	private final Action action;
	private final RepoRepository repoRepository;
	private final BatchTokenRepository batchTokenRepository;
	private final BatchTokenService batchTokenService;

	@Bean
	public Job getEnterRepositoryJob(Step accessTokenTestStep, Step getEnterRepositoryStep, Step batchTokenResetStep) {
		return jobBuilderFactory.get("getEnterRepositoryJob")
			.incrementer(new RunIdIncrementer())
			.listener(new LoggerListener())
			.start(accessTokenTestStep)
			.on("FAILED").to(batchTokenResetStep).on("*").end()
			.from(accessTokenTestStep)
			.on("*").to(getEnterRepositoryStep).on("*").to(batchTokenResetStep).end()
			.build();
	}

	@JobScope
	@Bean
	public Step accessTokenTestStep() {
		return stepBuilderFactory.get("accessTokenTestStep")
			.<String, String>chunk(1)
			.reader(new AccessTokenTestReader(memberRepository, action))
			.writer(new AccessTokenTestWriter(batchTokenRepository))
			.build();
	}

	@JobScope
	@Bean
	public Step getEnterRepositoryStep() {
		return stepBuilderFactory.get("getEnterRepositoryStep")
			.<RepositoryDto, RepositoryDto>chunk(1)
			.reader(new GetEnterRepositoryReader(enterpriseRepository, action, batchTokenRepository))
			.writer(new GetMemberRepositoryWriter(repositoryService, repoRepository))
			.build();
	}

	@JobScope
	@Bean
	public Step batchTokenResetStep() {
		return stepBuilderFactory.get("batchTokenResetStep")
			.tasklet(new BatchTokenResetTasklet(batchTokenService))
			.build();
	}
}
