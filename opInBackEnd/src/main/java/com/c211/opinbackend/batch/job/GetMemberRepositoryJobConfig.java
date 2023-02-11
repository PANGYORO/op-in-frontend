package com.c211.opinbackend.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.c211.opinbackend.batch.dto.github.RepositoryDto;
import com.c211.opinbackend.batch.item.reader.GetMemberRepositoryReader;
import com.c211.opinbackend.batch.item.writer.GetMemberRepositoryWriter;
import com.c211.opinbackend.batch.listener.LoggerListener;
import com.c211.opinbackend.batch.service.RepositoryService;
import com.c211.opinbackend.persistence.repository.MemberRepository;

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

	@Bean
	public Job getMemberRepositoryJob(Step getMemberRepositoryStep) {
		return jobBuilderFactory.get("getMemberRepositoryJob")
			.incrementer(new RunIdIncrementer())
			.listener(new LoggerListener())
			.start(getMemberRepositoryStep)
			.build();
	}

	@JobScope
	@Bean
	public Step getMemberRepositoryStep() {
		return stepBuilderFactory.get("getMemberRepositoryStep")
			.<RepositoryDto, RepositoryDto>chunk(1)
			.reader(new GetMemberRepositoryReader(memberRepository))
			.writer(new GetMemberRepositoryWriter(repositoryService))
			.build();
	}

}
