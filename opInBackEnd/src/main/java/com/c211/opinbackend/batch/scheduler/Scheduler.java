package com.c211.opinbackend.batch.scheduler;

import java.util.Collections;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Scheduler {
	private final Job getMemberRepositoryJob;
	private final Job getRepoTechLanguageJob;
	private final Job getRepoCommitJob;
	private final Job getRepoPullRequestJob;
	private final Job getRepoContributorJob;

	private final JobLauncher jobLauncher;

	@Scheduled(cron = "0 0/59 0 * * *") // 매 1시간 마다
	public void getMemberRepositoryJobRus() throws
		JobInstanceAlreadyCompleteException,
		JobExecutionAlreadyRunningException,
		JobParametersInvalidException,
		JobRestartException {

		JobParameters jobParameters = new JobParameters(
			Collections.singletonMap("requestTime", new JobParameter(System.currentTimeMillis())));

		jobLauncher.run(getMemberRepositoryJob, jobParameters);
	}

	@Scheduled(cron = "0 0/59 1 * * *") // 매 2시간 마다
	public void getRepoTechLanguageJobRus() throws
		JobInstanceAlreadyCompleteException,
		JobExecutionAlreadyRunningException,
		JobParametersInvalidException,
		JobRestartException {

		JobParameters jobParameters = new JobParameters(
			Collections.singletonMap("requestTime", new JobParameter(System.currentTimeMillis())));

		jobLauncher.run(getRepoTechLanguageJob, jobParameters);
	}

	@Scheduled(cron = "0 0/59 2 * * *") // 매 3시간 마다
	public void getRepoCommitJobRus() throws
		JobInstanceAlreadyCompleteException,
		JobExecutionAlreadyRunningException,
		JobParametersInvalidException,
		JobRestartException {

		JobParameters jobParameters = new JobParameters(
			Collections.singletonMap("requestTime", new JobParameter(System.currentTimeMillis())));

		jobLauncher.run(getRepoCommitJob, jobParameters);
	}

	@Scheduled(cron = "0 0/59 3 * * *") // 매 4시간 마다
	public void getRepoPullRequestJobRus() throws
		JobInstanceAlreadyCompleteException,
		JobExecutionAlreadyRunningException,
		JobParametersInvalidException,
		JobRestartException {

		JobParameters jobParameters = new JobParameters(
			Collections.singletonMap("requestTime", new JobParameter(System.currentTimeMillis())));

		jobLauncher.run(getRepoPullRequestJob, jobParameters);
	}

	@Scheduled(cron = "0 0/59 4 * * *") // 매 시간 55분에
	public void getRepoContributorJobRus() throws
		JobInstanceAlreadyCompleteException,
		JobExecutionAlreadyRunningException,
		JobParametersInvalidException,
		JobRestartException {

		JobParameters jobParameters = new JobParameters(
			Collections.singletonMap("requestTime", new JobParameter(System.currentTimeMillis())));

		jobLauncher.run(getRepoContributorJob, jobParameters);
	}

}
