package com.c211.opinbackend.batch.step;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.c211.opinbackend.persistence.entity.BatchToken;
import com.c211.opinbackend.persistence.repository.BatchTokenRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BatchTokenResetTasklet implements Tasklet {

	private final BatchTokenRepository batchTokenRepository;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		batchTokenRepository.truncateBatchToken();
		return RepeatStatus.FINISHED;
	}
}
