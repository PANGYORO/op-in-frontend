package com.c211.opinbackend.batch.item.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.c211.opinbackend.batch.dto.github.CommitDto;
import com.c211.opinbackend.batch.dto.mapper.CommitHistoryMapper;
import com.c211.opinbackend.persistence.entity.CommitHistory;
import com.c211.opinbackend.persistence.repository.CommitHistoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetRepoCommitWriter implements ItemWriter<CommitDto> {

	private final CommitHistoryRepository commitHistoryRepository;
	private final CommitHistoryMapper commitHistoryMapper;

	@Override
	public void write(List<? extends CommitDto> items) throws Exception {
		for (CommitDto commit : items) {
			try {
				commitHistoryRepository.save(commitHistoryMapper.toCommitHistory(commit));
			} catch (Exception e) {
				log.info(e.toString());
			}
		}
	}

}
