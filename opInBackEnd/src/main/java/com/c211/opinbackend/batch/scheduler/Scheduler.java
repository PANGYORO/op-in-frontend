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
	private final Job getRepoContributorJob;
	private final Job getEnterRepositoryJob;
	private final Job getMemberTechLanguageJob;

	private final JobLauncher jobLauncher;

	@Scheduled(cron = "0 55 11 * * *")
	public void getMemberRepositoryJobRus() throws
		JobInstanceAlreadyCompleteException,
		JobExecutionAlreadyRunningException,
		JobParametersInvalidException,
		JobRestartException {

		JobParameters jobParameters = new JobParameters(
			Collections.singletonMap("requestTime", new JobParameter(System.currentTimeMillis())));

		jobLauncher.run(getMemberRepositoryJob, jobParameters);
	}

	@Scheduled(cron = "0 57 11 * * *")
	public void getEnterRepositoryJobRus() throws
		JobInstanceAlreadyCompleteException,
		JobExecutionAlreadyRunningException,
		JobParametersInvalidException,
		JobRestartException {

		JobParameters jobParameters = new JobParameters(
			Collections.singletonMap("requestTime", new JobParameter(System.currentTimeMillis())));

		jobLauncher.run(getEnterRepositoryJob, jobParameters);
	}

	@Scheduled(cron = "0 24 13 * * *")
	public void getRepoTechLanguageJobRus() throws
		JobInstanceAlreadyCompleteException,
		JobExecutionAlreadyRunningException,
		JobParametersInvalidException,
		JobRestartException {

		JobParameters jobParameters = new JobParameters(
			Collections.singletonMap("requestTime", new JobParameter(System.currentTimeMillis())));

		jobLauncher.run(getRepoTechLanguageJob, jobParameters);
	}

	@Scheduled(cron = "0 30 13 * * *") // 매 시간 55분에
	public void getRepoContributorJobRus() throws
		JobInstanceAlreadyCompleteException,
		JobExecutionAlreadyRunningException,
		JobParametersInvalidException,
		JobRestartException {

		JobParameters jobParameters = new JobParameters(
			Collections.singletonMap("requestTime", new JobParameter(System.currentTimeMillis())));

		jobLauncher.run(getRepoContributorJob, jobParameters);
	}

	@Scheduled(cron = "0 52 13 * * *") // 매 시간 55분에
	public void getMemberTechLanguageJobRus() throws
		JobInstanceAlreadyCompleteException,
		JobExecutionAlreadyRunningException,
		JobParametersInvalidException,
		JobRestartException {

		JobParameters jobParameters = new JobParameters(
			Collections.singletonMap("requestTime", new JobParameter(System.currentTimeMillis())));

		jobLauncher.run(getMemberTechLanguageJob, jobParameters);
	}

}
