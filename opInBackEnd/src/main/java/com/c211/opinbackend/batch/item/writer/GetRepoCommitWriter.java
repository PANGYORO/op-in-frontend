package com.c211.opinbackend.batch.item.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.c211.opinbackend.batch.dto.RepoTechLanguageDto;
import com.c211.opinbackend.batch.dto.github.CommitDto;
import com.c211.opinbackend.persistence.entity.CommitHistory;
import com.c211.opinbackend.persistence.entity.RepositoryTechLanguage;
import com.c211.opinbackend.persistence.entity.TechLanguage;
import com.c211.opinbackend.persistence.repository.CommitHistoryRepository;
import com.c211.opinbackend.persistence.repository.RepoTechLanguageRepository;
import com.c211.opinbackend.persistence.repository.TechLanguageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetRepoCommitWriter implements ItemWriter<CommitDto> {

	private final CommitHistoryRepository commitHistoryRepository;

	@Override
	public void write(List<? extends CommitDto> items) throws Exception {
		for (CommitDto commit : items) {
			// language 가 Tech language 테이블에 존재한다면 넘어가고, 아니면 저장한 후
			// member Tech language 테이블에 관계 저장
			try {

				// commitHistoryRepository.save(
				// 	CommitHistory.builder()
				// 		.sha(commit.getSha())
				// 		.repository(commit.getRepo())
				// 		.date()
				// 		.message()
				// 		.authorId()
				// 		.authorName()
				// 		.authorAvatarUrl()
				// 		.parent()
				// 		.build()
				// );
			} catch (Exception e) {
				log.info(e.toString());
			}
		}
	}
}
