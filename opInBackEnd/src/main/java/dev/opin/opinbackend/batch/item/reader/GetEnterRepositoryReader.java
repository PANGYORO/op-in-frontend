package dev.opin.opinbackend.batch.item.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import dev.opin.opinbackend.batch.dto.github.RepositoryDto;
import dev.opin.opinbackend.batch.step.Action;
import dev.opin.opinbackend.persistence.entity.BatchToken;
import dev.opin.opinbackend.persistence.entity.Enterprise;
import dev.opin.opinbackend.persistence.repository.BatchTokenRepository;
import dev.opin.opinbackend.persistence.repository.EnterpriseRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetEnterRepositoryReader implements ItemReader<RepositoryDto> {

	private final EnterpriseRepository enterpriseRepository;
	private final Action action;
	private final BatchTokenRepository batchTokenRepository;

	private List<RepositoryDto> collectData = new ArrayList<>(); //Rest로 가져온 데이터를 리스트에 넣는다.
	private boolean checkRestCall = false; //RestAPI 호출여부 판단
	private int nextIndex = 0; //리스트의 데이터를 하나씩 인덱스를 통해 가져온다.

	@Override
	public RepositoryDto read() throws Exception,
		UnexpectedInputException, ParseException, NonTransientResourceException {

		if (checkRestCall == false) { //한번도 호출 않았는지 체크
			// token 초기화
			List<BatchToken> batchTokens = batchTokenRepository.findAll();
			int index = 0;
			String githubToken = batchTokens.get(index).getAccessToken();

			List<Enterprise> enters = enterpriseRepository.findAll();
			for (Enterprise enter : enters) {
				List<RepositoryDto> result = new ArrayList<>();
				int page = 1;

				while (true) {
					try {
						RepositoryDto[] repositoryDtos = action.getMemberRepository(githubToken,
							enter.getTitle(),
							String.valueOf(page));

						result.addAll(Arrays.asList(repositoryDtos));
						if (repositoryDtos.length < 100) {
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
			checkRestCall = true; //다음 read() 부터는 재호출 방지하기 위해 true로 변경
		}

		RepositoryDto nextCollect = null; // ItemReader는 반복문으로 동작한다. 하나씩 Writer로 전달해야 한다.

		if (nextIndex < collectData.size()) { //전체 리스트에서 하나씩 추출해서, 하나씩 Writer로 전달
			nextCollect = collectData.get(nextIndex);
			nextIndex++;
		}

		return nextCollect; //DTO 하나씩 반환한다. Rest 호출시 데이터가 없으면 null로 반환.
	}

}
