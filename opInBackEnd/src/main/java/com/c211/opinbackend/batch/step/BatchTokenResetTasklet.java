package com.c211.opinbackend.batch.step;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.c211.opinbackend.batch.service.BatchTokenService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BatchTokenResetTasklet implements Tasklet {

	private final BatchTokenService batchTokenService;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		batchTokenService.deleteBatchToken();
		return RepeatStatus.FINISHED;
	}
}
