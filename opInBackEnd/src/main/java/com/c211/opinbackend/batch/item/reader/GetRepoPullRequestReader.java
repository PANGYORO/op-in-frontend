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

import com.c211.opinbackend.batch.dto.github.CommitDto;
import com.c211.opinbackend.batch.dto.github.PullRequestDto;
import com.c211.opinbackend.batch.dto.mapper.PullRequestMapper;
import com.c211.opinbackend.batch.step.Action;
import com.c211.opinbackend.constant.GitHub;
import com.c211.opinbackend.persistence.entity.BatchToken;
import com.c211.opinbackend.persistence.entity.PullRequest;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.repository.BatchTokenRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetRepoPullRequestReader implements ItemReader<PullRequest> {

	private List<PullRequest> collectData = new ArrayList<>(); //Rest로 가져온 데이터를 리스트에 넣는다.
	private boolean checkRestCall = false; //RestAPI 호출여부 판단
	private int nextIndex = 0;//리스트의 데이터를 하나씩 인덱스를 통해 가져온다.
	private final RepoRepository repoRepository;
	private final PullRequestMapper pullRequestMapper;
	private final Action action;
	private final BatchTokenRepository batchTokenRepository;

	@Override
	public PullRequest read() throws
		Exception,
		UnexpectedInputException,
		ParseException,
		NonTransientResourceException {

		if (checkRestCall == false) {//한번도 호출 않았는지 체크
			// token 초기화
			List<BatchToken> batchTokens = batchTokenRepository.findAll();
			int index = 0;
			String githubToken = batchTokens.get(index).getAccessToken();

			List<Repository> repos = repoRepository.findAll();

			for (Repository repo : repos) {
				List<PullRequest> result = new ArrayList<>();
				int page = 1;

				while (true) {
					try {
						PullRequest[] pullRequests = Arrays.stream(action.getRepositoryPulls(githubToken, repo.getFullName(), String.valueOf(page))).map(
							prDto -> pullRequestMapper.toPullRequest(prDto, repo)
						).toArray(PullRequest[]::new);
						result.addAll(Arrays.asList(pullRequests));
						if (pullRequests.length < 100) {
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

				collectData.addAll(result);
			}

			log.info("Rest Call result : >>>>>>>" + collectData.size());
			checkRestCall = true;//다음 read() 부터는 재호출 방지하기 위해 true로 변경
		}

		PullRequest nextCollect = null; // ItemReader는 반복문으로 동작한다. 하나씩 Writer로 전달해야 한다.

		if (nextIndex < collectData.size()) {//전체 리스트에서 하나씩 추출해서, 하나씩 Writer로 전달
			nextCollect = collectData.get(nextIndex);
			nextIndex++;
		}

		return nextCollect;//DTO 하나씩 반환한다. Rest 호출시 데이터가 없으면 null로 반환.
	}

}
