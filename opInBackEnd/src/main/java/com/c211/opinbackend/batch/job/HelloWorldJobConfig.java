package com.c211.opinbackend.batch.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.batch.api.chunk.ItemReader;

import org.hibernate.cfg.Environment;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.c211.opinbackend.batch.dto.RepoTechLanguageDto;
import com.c211.opinbackend.batch.dto.github.RepositoryDto;
import com.c211.opinbackend.batch.item.reader.GetMemberRepositoryReader;
import com.c211.opinbackend.batch.item.reader.GetRepoTechLanguageReader;
import com.c211.opinbackend.batch.item.writer.GetMemberRepositoryWriter;
import com.c211.opinbackend.batch.item.writer.GetRepoTechLanguageWriter;
import com.c211.opinbackend.batch.listener.LoggerListener;
import com.c211.opinbackend.batch.service.RepositoryService;
import com.c211.opinbackend.constant.GitHub;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.persistence.repository.RepoTechLanguageRepository;
import com.c211.opinbackend.persistence.repository.TechLanguageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class HelloWorldJobConfig {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	private final RepositoryService repositoryService;

	private final MemberRepository memberRepository;

	private final RepoRepository repoRepository;

	private final TechLanguageRepository techLanguageRepository;

	private final RepoTechLanguageRepository repoTechLanguageRepository;


	@Bean
	public Job helloJob(Step getMemberRepositoryStep, Step getAllRepositoryTechLanguageStep) {
		return jobBuilderFactory.get("helloJob")
			.incrementer(new RunIdIncrementer())
			.listener(new LoggerListener())
			.start(getMemberRepositoryStep)
			.next(getAllRepositoryTechLanguageStep)
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

	@JobScope
	@Bean
	public Step getAllRepositoryTechLanguageStep() {
		return stepBuilderFactory.get("getAllRepositoryTechLanguageStep")
			.<RepoTechLanguageDto
				, RepoTechLanguageDto>chunk(1)
			.reader(new GetRepoTechLanguageReader(repoRepository))
			.writer(new GetRepoTechLanguageWriter(techLanguageRepository, repoTechLanguageRepository))
			.build();
	}

}
