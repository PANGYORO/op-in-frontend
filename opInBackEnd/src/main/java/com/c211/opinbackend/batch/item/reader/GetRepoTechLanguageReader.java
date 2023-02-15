package com.c211.opinbackend.batch.item.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.c211.opinbackend.batch.dto.RepoTechLanguageDto;
import com.c211.opinbackend.batch.dto.github.RepositoryDto;
import com.c211.opinbackend.batch.step.Action;
import com.c211.opinbackend.persistence.entity.BatchToken;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.repository.BatchTokenRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetRepoTechLanguageReader implements ItemReader<RepoTechLanguageDto> {

	private final RepoRepository repoRepository;
	private List<RepoTechLanguageDto> collectData = new ArrayList<>(); //Rest로 가져온 데이터를 리스트에 넣는다.
	private boolean checkRestCall = false; //RestAPI 호출여부 판단
	private int nextIndex = 0;//리스트의 데이터를 하나씩 인덱스를 통해 가져온다.
	private final Action action;
	private final BatchTokenRepository batchTokenRepository;

	@Override
	public RepoTechLanguageDto read() throws Exception,
		UnexpectedInputException, ParseException, NonTransientResourceException {

		if (checkRestCall == false) { //한번도 호출 않았는지 체크
			// token 초기화
			List<BatchToken> batchTokens = batchTokenRepository.findAll();
			int index = 0;
			String githubToken = batchTokens.get(index).getAccessToken();

			List<Repository> repos = repoRepository.findAll();
			for (Repository repo : repos) {

				List<RepositoryDto> result = new ArrayList<>();
				int page = 1;

				while (true) {
					try {
						Map<String, Long> languages = action.getRepositoryLanguages(githubToken, repo.getFullName(),
							String.valueOf(page));
						for (String lan : languages.keySet()) {
							collectData.add(RepoTechLanguageDto.builder().repository(repo).language(lan).build());
						}
						if (languages.size() < 100) {
							break;
						}
						page += 1;
					} catch (Exception e) {
						index += 1;
						if (batchTokens.size() <= index) {
							break;
						}
						githubToken = batchTokens.get(index).getAccessToken();
					}
				}
			}

			log.info("Rest Call result : >>>>>>>" + collectData.size());
			checkRestCall = true;//다음 read() 부터는 재호출 방지하기 위해 true로 변경
		}

		RepoTechLanguageDto nextCollect = null; // ItemReader는 반복문으로 동작한다. 하나씩 Writer로 전달해야 한다.

		if (nextIndex < collectData.size()) {//전체 리스트에서 하나씩 추출해서, 하나씩 Writer로 전달
			nextCollect = collectData.get(nextIndex);
			nextIndex++;
		}

		return nextCollect;
	}

}
