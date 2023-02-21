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
import dev.opin.opinbackend.batch.item.reader.GetMemberRepositoryReader;
import dev.opin.opinbackend.batch.item.writer.GetMemberRepositoryWriter;
import dev.opin.opinbackend.batch.listener.LoggerListener;
import dev.opin.opinbackend.batch.service.RepositoryService;
import dev.opin.opinbackend.batch.step.Action;
import dev.opin.opinbackend.persistence.repository.BatchTokenRepository;
import dev.opin.opinbackend.persistence.repository.MemberRepository;
import dev.opin.opinbackend.persistence.repository.RepoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GetMemberRepositoryJobConfig {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final RepositoryService repositoryService;
	private final MemberRepository memberRepository;
	private final Action action;
	private final BatchTokenRepository batchTokenRepository;
	private final RepoRepository repoRepository;

	@Bean
	public Job getMemberRepositoryJob(Step accessTokenTestStep, Step getMemberRepositoryStep,
		Step batchTokenResetStep) {
		return jobBuilderFactory.get("getMemberRepositoryJob")
			.incrementer(new RunIdIncrementer())
			.listener(new LoggerListener())
			.start(accessTokenTestStep)
			.on("FAILED").to(batchTokenResetStep).on("*").end()
			.from(accessTokenTestStep)
			.on("*").to(getMemberRepositoryStep).on("*").to(batchTokenResetStep).end()
			.build();
	}

	@JobScope
	@Bean
	public Step getMemberRepositoryStep() {
		return stepBuilderFactory.get("getMemberRepositoryStep")
			.<RepositoryDto, RepositoryDto>chunk(1)
			.reader(new GetMemberRepositoryReader(memberRepository, action, batchTokenRepository))
			.writer(new GetMemberRepositoryWriter(repositoryService, repoRepository))
			.build();
	}

}
