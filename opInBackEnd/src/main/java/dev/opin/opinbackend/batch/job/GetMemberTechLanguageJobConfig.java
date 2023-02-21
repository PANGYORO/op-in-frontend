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
import dev.opin.opinbackend.batch.step.GetMemberTechLanguageTasklet;
import dev.opin.opinbackend.persistence.repository.MemberTechLanguageRepository;
import dev.opin.opinbackend.persistence.repository.RepoRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class GetMemberTechLanguageJobConfig {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final RepoRepository repoRepository;
	private final MemberTechLanguageRepository memberTechLanguageRepository;

	@Bean
	public Job getMemberTechLanguageJob(Step accessTokenTestStep, Step getMemberTechLanguageStep,
		Step batchTokenResetStep) {
		return jobBuilderFactory.get("getMemberTechLanguageJob")
			.incrementer(new RunIdIncrementer())
			.listener(new LoggerListener())
			.start(accessTokenTestStep)
			.on("FAILED").to(batchTokenResetStep).on("*").end()
			.from(accessTokenTestStep)
			.on("*").to(getMemberTechLanguageStep).on("*").to(batchTokenResetStep).end()
			.build();
	}

	@JobScope
	@Bean
	public Step getMemberTechLanguageStep() {
		return stepBuilderFactory.get("getMemberTechLanguageStep")
			.tasklet(
				new GetMemberTechLanguageTasklet(memberTechLanguageRepository, repoRepository))
			.build();
	}
}
