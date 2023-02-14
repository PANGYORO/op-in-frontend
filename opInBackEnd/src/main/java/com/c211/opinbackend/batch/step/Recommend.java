package com.c211.opinbackend.batch.step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.c211.opinbackend.persistence.entity.RepositoryFollow;
import com.c211.opinbackend.persistence.repository.RepositoryFollowRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Recommend {

	private final List<Map<String, Double>> similarity;
	// private final Map<Long, Map<Long, ItemCounter>> matrix;
	// private final Map<Long, Map<Long, Double>> dataByMember;
	private final RepositoryFollowRepository repositoryFollowRepository;

	public Map<String, List<String>> findRelationship() {
		// repository_follow 관계를 돌면서 map에 key = from member, value = to repository (String 값 더하기) 후 return
		List<RepositoryFollow> repoFollow = repositoryFollowRepository.findAll();

		Map<String, List<String>> repoFollowMap = new HashMap<>();

		for (RepositoryFollow follow : repoFollow) {
			String memberId = String.valueOf(follow.getMember().getId());
			List<String> followRepo = new ArrayList<>();
			followRepo.add(String.valueOf(follow.getRepository().getId()));

			if (repoFollowMap.get(memberId) == null) {
				repoFollowMap.put(memberId, followRepo);
			}else {
				List<String> on = repoFollowMap.get(memberId);
				on.addAll(followRepo);
				repoFollowMap.put(memberId, on);
			}

		}

		return repoFollowMap;
	}

	public List<Map<String, Double>> getMySimilarityList(String me, String myRepositories, Map<String, List<String>> repoFollowMap) {
		List<Map<String, Double>> mySimilarity = new ArrayList<>();

		// 자기 자신과 나머지들의 문자열 유사도 계산 후
		// 유사도 List의 0번 값보다 유사도가 높으면 그 앞에, 아니라면 그 뒤에 추가 후 return
		// 자기 자신 제거하기
		for (Map.Entry<String, List<String>> entry : repoFollowMap.entrySet()) {
			String fromMember = entry.getKey();
			String repositories = String.join("", entry.getValue());

			Map<String, Double> value = new HashMap<>();
			value.put(fromMember, findSimilarity(myRepositories, repositories));

			// 뒤에 순서도 매겨야 됨 -> deque로 수정
			// mySimilarity List 를 정렬 -> List 안에 Map<memberId, 유사도Double>
			// List를 하지 말고 class
			// TODO: 2023-02-14
			if (mySimilarity != null && mySimilarity.size() > 0 &&
				Double.compare(mySimilarity.get(0).get(mySimilarity.get(0).keySet().toArray()[0]), value.get(fromMember)) > 0) {
				mySimilarity.add(value);
			}else {
				mySimilarity.add(0, value);
			}
		}
		return mySimilarity;
	}

	public void prepareMatrix() {

		// 유사도 계산 ->
		// Map<String, List<String>> repoFollowMap -> key = from member, value = to repository List
		Map<String, List<String>> repoFollowMap = findRelationship();

		for (Map.Entry<String, List<String>> entry : repoFollowMap.entrySet()) {
			String me = entry.getKey();
			String myRepositories = String.join("", entry.getValue());

			// 유사도 List
			// List<Map<String 유사도 비교 멤버 id, Double 유사도>>
			List<Map<String, Double>> mySimilarityList = getMySimilarityList(me, myRepositories, repoFollowMap);

			// 추천 ->
			// 유사도 List 를 위에서부터 돌면서
			// 그 사용자들의 팔로우 레포지토리들 중 내가 팔로우하지 않는 레포지토리들 추출
			// 100개가 된다면 stop / 그보다 작아도 유사도 List가 끝난다면 stop
			for (Map<String, Double> map: mySimilarityList) {
			}

			// 후 해당 레포지토리들의 tech language 와 내 tech language 문자열 유사도 비교
			// 후 유사한 순으로 정렬
			// 후 깃허브 스타 수를 기준으로 정렬 return
		}

		/**
		for (DataModel model : dataModel) {
			final Long memberId = model.getMemberId();
			final Map<Long, Double> itemByMember = dataByMember
				.computeIfAbsent(memberId, id -> new HashMap<>());

			for (Entry<Long, Double> itemPreference : itemByMember.entrySet()) {
				final Long itemId = itemPreference.getKey();
				final Double preference = itemPreference.getValue();
				if(itemId.equals(model.getItemId())) continue;

				final Map<Long, ItemCounter> primaryMap =
					matrix.computeIfAbsent(model.getItemId(), id -> new HashMap<>());
				final Map<Long, ItemCounter> secondaryMap =
					matrix.computeIfAbsent(itemId, id -> new HashMap<>());

				primaryMap.computeIfAbsent(itemId, id -> new ItemCounter()).addSum(model.getPreference() - preference);
				secondaryMap.computeIfAbsent(model.getItemId(), id -> new ItemCounter()).addSum(preference - model.getPreference());
			}
			itemByMember.put(model.getItemId(), model.getPreference());
		}
		*/
	}


	/**
	public Map<Long, Map<Long, ItemCounter>> getMatrix() {
		return matrix;
	}

	public Map<Long, Double> getDataByMember(Long id) {
		return dataByMember.getOrDefault(id, new HashMap<>());
	}
	 */

	public static double findSimilarity(String x, String y) {
		double maxLength = Double.max(x.length(), y.length());
		if (maxLength > 0) {
			// 필요한 경우 선택적으로 대소문자를 무시합니다.
			return (maxLength - StringUtils.getLevenshteinDistance(x, y)) / maxLength;
		}
		return 1.0;
	}

}
