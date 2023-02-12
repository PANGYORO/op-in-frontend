package com.c211.opinbackend.batch.item.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import com.c211.opinbackend.batch.dto.RepoTechLanguageDto;
import com.c211.opinbackend.constant.GitHub;
import com.c211.opinbackend.persistence.entity.Repository;
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

	@Override
	public RepoTechLanguageDto read() throws Exception,
		UnexpectedInputException, ParseException, NonTransientResourceException {

		if (checkRestCall == false) {//한번도 호출 않았는지 체크
			List<Repository> repos = repoRepository.findAll();

			for (Repository repo : repos) {
				Map<String, Long> languages = getRepositoryLanguages(repo.getFullName());
				for (String lan : languages.keySet()) {
					collectData.add(RepoTechLanguageDto.builder().repository(repo).language(lan).build());
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

	public static Map<String, Long> getRepositoryLanguages(String repositoryFullName) {
		return WebClient.create()
			.get()
			.uri(
				GitHub.getPublicRepositoryLanguageUrl(repositoryFullName)
			)
			.header("Authorization", "token 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")
			.retrieve().bodyToMono(new ParameterizedTypeReference<Map<String, Long>>() {
			}).block();
	}

}
