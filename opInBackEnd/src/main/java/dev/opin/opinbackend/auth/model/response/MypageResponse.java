package dev.opin.opinbackend.auth.model.response;

import java.util.List;

import dev.opin.opinbackend.repo.model.response.RepositoryPostResponse;
import dev.opin.opinbackend.repo.model.response.RepositoryTitleResponse;
import dev.opin.opinbackend.repo.model.response.TopicResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MypageResponse {

	private String nickname;

	private String avataUrl;

	private List<RepositoryTitleResponse> myRepoTitles;

	private List<RepositoryPostResponse> posts;

	private long countFollowing;

	private long countFollower;

	private List<BadgeResponse> badges;

	private List<TechLanguageResponse> techLanguages;

	private List<TopicResponse> topicResponses;

	private List<RepositoryTitleResponse> contributeRepo;

	private List<RepositoryTitleResponse> followRepos;
}
