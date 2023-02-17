package com.c211.opinbackend.batch.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerListener implements JobExecutionListener {

	private static String BEFORE_MESSAGE = "{} Job is Running";

	private static String AFTER_MESSAGE = "{} Job is Done. (status: {})";

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info(BEFORE_MESSAGE, jobExecution.getJobInstance().getJobName());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info(AFTER_MESSAGE, jobExecution.getJobInstance().getJobName(), jobExecution.getStatus());

		// 만약 batch가 실패한다면
		if (jobExecution.getStatus() == BatchStatus.FAILED) {
			// email 이나 메신저로 fail 결과 받을 수 있게 구현 가능
			// 지금은 단순히 로그 확인
			log.info("Job is Failed");
		}
	}
}
