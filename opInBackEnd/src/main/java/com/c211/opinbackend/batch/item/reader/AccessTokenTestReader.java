package com.c211.opinbackend.batch.item.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.c211.opinbackend.batch.step.Action;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AccessTokenTestReader implements ItemReader<String> {

	private final MemberRepository memberRepository;
	private final Action action;
	private List<String> collectData = new ArrayList<>(); //Rest로 가져온 데이터를 리스트에 넣는다.
	private boolean checkRestCall = false; //RestAPI 호출여부 판단
	private int nextIndex = 0;//리스트의 데이터를 하나씩 인덱스를 통해 가져온다.

	@Override
	public String read() throws Exception,
		UnexpectedInputException, ParseException, NonTransientResourceException {

		if (checkRestCall == false){//한번도 호출 않았는지 체크

			List<Member> members = memberRepository.findAllByGithubIdIsNotNull();

			for (Member member : members) {
				try {
					action.getMemberRepository(member.getGithubToken(), member.getGithubUserName(), "1");
				} catch (Exception e) {

				}
				collectData.add(member.getGithubToken());
			}

			log.info("Rest Call result : >>>>>>>" + collectData.size());
			checkRestCall = true;//다음 read() 부터는 재호출 방지하기 위해 true로 변경
		}

		String nextCollect = null; // ItemReader는 반복문으로 동작한다. 하나씩 Writer로 전달해야 한다.

		if (nextIndex < collectData.size()) {//전체 리스트에서 하나씩 추출해서, 하나씩 Writer로 전달
			nextCollect = collectData.get(nextIndex);
			nextIndex++;
		}

		return nextCollect;//DTO 하나씩 반환한다. Rest 호출시 데이터가 없으면 null로 반환.
	}
}
