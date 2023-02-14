package com.c211.opinbackend.batch.item.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.c211.opinbackend.batch.dto.github.RepositoryDto;
import com.c211.opinbackend.batch.service.RepositoryService;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.repository.RepoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetMemberRepositoryWriter implements ItemWriter<RepositoryDto> {

	private final RepositoryService repositoryService;
	private final RepoRepository repoRepository;

	@Override
	public void write(List<? extends RepositoryDto> items) throws Exception {
		for (RepositoryDto repo : items) {
			try {
				Repository repository = repoRepository.findById(repo.getId()).orElse(null);
				repositoryService.saveOrUpdateRepository(repo);
			} catch (Exception e) {
				log.info(e.toString());
			}
		}
	}
}
