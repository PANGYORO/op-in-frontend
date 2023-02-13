package com.c211.opinbackend.batch.item.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import com.c211.opinbackend.batch.dto.github.ContributorDto;
import com.c211.opinbackend.batch.step.Action;
import com.c211.opinbackend.constant.GitHub;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.repository.RepoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetRepoContributorReader implements ItemReader<ContributorDto> {

	private List<ContributorDto> collectData = new ArrayList<>(); //Rest로 가져온 데이터를 리스트에 넣는다.
	private boolean checkRestCall = false; //RestAPI 호출여부 판단
	private int nextIndex = 0;//리스트의 데이터를 하나씩 인덱스를 통해 가져온다.
	private final RepoRepository repoRepository;
	private final Action action;
	private final String githubToken;

	@Override
	public ContributorDto read() throws
		Exception,
		UnexpectedInputException,
		ParseException,
		NonTransientResourceException {

		if (checkRestCall == false) {//한번도 호출 않았는지 체크
			List<Repository> repos = repoRepository.findAll();

			for (Repository repo : repos) {
				ContributorDto[] contributorDtos = action.getContributors(githubToken, repo.getFullName());
				for (ContributorDto con : contributorDtos) {
					con.setRepository(repo);
				}
				List<ContributorDto> contributorDtoList = Arrays.asList(contributorDtos);
				collectData.addAll(contributorDtoList);
			}

			log.info("Rest Call result : >>>>>>>" + collectData.size());
			checkRestCall = true;//다음 read() 부터는 재호출 방지하기 위해 true로 변경
		}

		ContributorDto nextCollect = null; // ItemReader는 반복문으로 동작한다. 하나씩 Writer로 전달해야 한다.

		if (nextIndex < collectData.size()) {//전체 리스트에서 하나씩 추출해서, 하나씩 Writer로 전달
			nextCollect = collectData.get(nextIndex);
			nextIndex++;
		}

		return nextCollect;//DTO 하나씩 반환한다. Rest 호출시 데이터가 없으면 null로 반환.
	}

}
