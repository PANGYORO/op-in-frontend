package com.c211.opinbackend.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.c211.opinbackend.auth.jwt.TokenProvider;
import com.c211.opinbackend.auth.model.response.BadgeResponse;
import com.c211.opinbackend.auth.model.response.MypageResponse;
import com.c211.opinbackend.auth.model.response.TechLanguageResponse;
import com.c211.opinbackend.exception.api.ApiExceptionEnum;
import com.c211.opinbackend.exception.api.ApiRuntimeException;
import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.persistence.entity.Badge;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.MemberBadge;
import com.c211.opinbackend.persistence.entity.MemberTechLanguage;
import com.c211.opinbackend.persistence.entity.MemberTopic;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryContributor;
import com.c211.opinbackend.persistence.entity.RepositoryFollow;
import com.c211.opinbackend.persistence.entity.RepositoryPost;
import com.c211.opinbackend.persistence.entity.TechLanguage;
import com.c211.opinbackend.persistence.entity.Topic;
import com.c211.opinbackend.persistence.repository.BadgeRepository;
import com.c211.opinbackend.persistence.repository.CommentRepository;
import com.c211.opinbackend.persistence.repository.MemberBadgeRepository;
import com.c211.opinbackend.persistence.repository.MemberFollowRepository;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.persistence.repository.MemberTechLanguageRepository;
import com.c211.opinbackend.persistence.repository.MemberTopicRepository;
import com.c211.opinbackend.persistence.repository.RepoContributorRepository;
import com.c211.opinbackend.persistence.repository.RepoPostRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.persistence.repository.RepositoryFollowRepository;
import com.c211.opinbackend.persistence.repository.RepositoryPostMemberLikeRepository;
import com.c211.opinbackend.persistence.repository.TechLanguageRepository;
import com.c211.opinbackend.persistence.repository.TopicRepository;
import com.c211.opinbackend.repo.model.response.RepositoryPostResponse;
import com.c211.opinbackend.repo.model.response.RepositoryTitleResponse;
import com.c211.opinbackend.repo.model.response.TopicResponse;
import com.c211.opinbackend.util.SecurityUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final TokenProvider tokenProvider;
	private final MemberRepository memberRepository;
	private final MemberFollowRepository memberFollowRepository;
	private final MemberBadgeRepository memberBadgeRepository;
	private final MemberTechLanguageRepository memberTechLanguageRepository;
	private final MemberTopicRepository memberTopicRepository;
	private final BadgeRepository badgeRepository;
	private final TopicRepository topicRepository;
	private final TechLanguageRepository techLanguageRepository;
	private final RepoRepository repoRepository;
	private final RepoPostRepository repoPostRepository;
	private final CommentRepository commentRepository;
	private final RepositoryPostMemberLikeRepository repositoryPostMemberLikeRepository;
	private final RepositoryFollowRepository repositoryFollowRepository;
	private final RepoContributorRepository repoContributorRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Optional<Member> findByEmail(String email) {
		Optional<Member> byEmail = memberRepository.findByEmail(email);
		return byEmail;
	}

	@Override
	public List<Member> getGitHubSyncMembers() {
		return memberRepository.findAllByGithubIdIsNotNull();
	}

	@Override
	public boolean existEmail(String email) {
		return memberRepository.existsByEmail(email);
	}

	@Override
	public boolean existNickname(String nickname) {
		return memberRepository.existsByNickname(nickname);
	}

	@Override
	public boolean isOAuthMember(String email) {
		return memberRepository.existsByEmailAndAndGithubSyncFl(email, true);
	}

	public boolean deleteMember(String email, String password) {

		Member member = memberRepository.findByEmail(email).orElse(null);
		if (!passwordEncoder.matches(password, member.getPassword())) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION);
		}

		try {
			memberRepository.delete(member);
		} catch (Exception ex) {
			throw new ApiRuntimeException(ApiExceptionEnum.API_CENTER_CALL_EXCEPTION);
		}

		return true;
	}

	@Override
	public boolean deleteGithubMember(String email) {

		Member member = memberRepository.findByEmail(email).orElse(null);

		try {
			memberRepository.delete(member);
		} catch (Exception ex) {
			throw new ApiRuntimeException(ApiExceptionEnum.API_CENTER_CALL_EXCEPTION);
		}

		return true;
	}

	/*
	 * GET 사용자의 레포지토리 이름 목록
	 */
	public List<RepositoryTitleResponse> getMemberRepo(Member member) {

		List<Repository> myRepos = repoRepository.findByMember(member);
		List<RepositoryTitleResponse> myRepoTitles = new ArrayList<RepositoryTitleResponse>();

		for (Repository myRepo : myRepos) {
			RepositoryTitleResponse repositoryTitleResponse = RepositoryTitleResponse.builder()
				.id(myRepo.getId())
				.title(myRepo.getName())
				.build();

			myRepoTitles.add(repositoryTitleResponse);
		}

		return myRepoTitles;
	}

	/*
	 * GET 사용자가 쓴 포스트 목록
	 * */
	public List<RepositoryPostResponse> getMemberRepoPost(Member member) {
		List<RepositoryPost> myPosts = repoPostRepository.findByMember(member);
		List<RepositoryPostResponse> myRepoPosts = new ArrayList<RepositoryPostResponse>();

		for (RepositoryPost myPost : myPosts) {
			RepositoryPostResponse repositoryPostResponse = RepositoryPostResponse.builder()
				.id(myPost.getId())
				.title(myPost.getTitleContent().getTitle())
				.content(myPost.getTitleContent().getContent())
				.imageUrl(myPost.getImageUrl())
				.mergeFL(myPost.getMergeFL())
				.date(myPost.getDate())
				.closeState(myPost.getCloseState())
				.commentCount(commentRepository.countByRepositoryPost(myPost))
				.likeCount(repositoryPostMemberLikeRepository.countByRepositoryPost(myPost))
				.repoTitle(myPost.getRepository().getName())
				.build();

			myRepoPosts.add(repositoryPostResponse);
		}

		return myRepoPosts;
	}

	/*
	 * GET 사용자 뱃지 목록
	 * */
	public List<BadgeResponse> getMemberBadge(Member member) {
		List<MemberBadge> memBadRelations = memberBadgeRepository.findByMember(member);
		List<BadgeResponse> myBadges = new ArrayList<BadgeResponse>();
		for (MemberBadge mb : memBadRelations) {
			Badge badge = mb.getBadge();

			BadgeResponse badgeResponse = BadgeResponse.builder()
				.title(badge.getTitle())
				.imageUrl(badge.getImageUrl())
				.build();

			myBadges.add(badgeResponse);
		}

		return myBadges;
	}

	/*
	 * GET 사용자 언어 목록
	 * */
	public List<TechLanguageResponse> getMemberTechLanguage(Member member) {
		List<MemberTechLanguage> memTechRelations = memberTechLanguageRepository.findByMember(member);
		List<TechLanguageResponse> myTechLanguages = new ArrayList<TechLanguageResponse>();
		for (MemberTechLanguage mt : memTechRelations) {
			TechLanguage tech = mt.getTechLanguage();

			TechLanguageResponse techLanguageResponse = TechLanguageResponse.builder()
				.id(tech.getId())
				.title(tech.getTitle())
				.build();

			myTechLanguages.add(techLanguageResponse);
		}
		return myTechLanguages;
	}

	/*
	 * GET 사용자 토픽 목록
	 * */
	public List<TopicResponse> getMemberTopic(Member member) {
		List<MemberTopic> memTopicRelations = memberTopicRepository.findByMember(member);
		List<TopicResponse> myTopics = new ArrayList<TopicResponse>();
		for (MemberTopic mtp : memTopicRelations) {
			Topic topic = mtp.getTopic();

			TopicResponse topicResponse = TopicResponse.builder()
				.id(topic.getId())
				.title(topic.getTitle())
				.build();

			myTopics.add(topicResponse);
		}

		return myTopics;
	}

	/*
	 * GET 사용자 기여 레포지토리 목록
	 * */
	public List<RepositoryTitleResponse> getMemberContributes(Member member) {
		List<RepositoryContributor> repositoryContributors = repoContributorRepository.findByMember(member);
		List<RepositoryTitleResponse> contributeRepos = new ArrayList<RepositoryTitleResponse>();
		for (RepositoryContributor rc : repositoryContributors) {
			Repository repo = rc.getRepository();

			RepositoryTitleResponse repositoryTitleResponse = RepositoryTitleResponse.builder()
				.id(repo.getId())
				.title(repo.getName())
				.build();

			contributeRepos.add(repositoryTitleResponse);
		}

		return contributeRepos;
	}

	/*
	 * GET 사용자 팔로우 레포지토리 목록
	 * */
	public List<RepositoryTitleResponse> getMemberFollows(Member member) {
		List<RepositoryFollow> repositoryFollows = repositoryFollowRepository.findByMember(member);
		List<RepositoryTitleResponse> followRepos = new ArrayList<RepositoryTitleResponse>();
		for (RepositoryFollow rf : repositoryFollows) {
			Repository repo = rf.getRepository();

			RepositoryTitleResponse repositoryTitleResponse = RepositoryTitleResponse.builder()
				.id(repo.getId())
				.title(repo.getName())
				.build();

			followRepos.add(repositoryTitleResponse);
		}

		return followRepos;
	}

	@Override
	public MypageResponse getMemberInfo(String email) {

		Member member = memberRepository.findByEmail(email).orElse(null);
		if (member == null) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION);
		}

		// 사용자의 레포지토리 이름 목록
		List<RepositoryTitleResponse> myRepoTitles = getMemberRepo(member);

		// 사용자가 쓴 포스트 목록
		List<RepositoryPostResponse> myRepoPosts = getMemberRepoPost(member);

		// 사용자 뱃지
		List<BadgeResponse> myBadges = getMemberBadge(member);

		// 사용자 언어
		List<TechLanguageResponse> myTechLanguages = getMemberTechLanguage(member);

		// 사용자 토픽
		List<TopicResponse> myTopics = getMemberTopic(member);

		// 사용자 기여 레포지토리
		List<RepositoryTitleResponse> contributeRepos = getMemberContributes(member);

		// 사용자 팔로우 레포지토리
		List<RepositoryTitleResponse> followRepos = getMemberFollows(member);

		MypageResponse mypageResponse = MypageResponse.builder()
			.nickname(member.getNickname())
			.avataUrl(member.getAvatarUrl())
			.myRepoTitles(myRepoTitles)
			.posts(myRepoPosts)
			.countFollower(memberFollowRepository.countByToMember(member))
			.countFollowing(memberFollowRepository.countByFromMember(member))
			.badges(myBadges)
			.techLanguages(myTechLanguages)
			.topicResponses(myTopics)
			.contributeRepo(contributeRepos)
			.followRepos(followRepos)
			.build();

		return mypageResponse;
	}

	@Override
	@Transactional
	public Member modifyNickname(String nickname, String email) {
		Member member = memberRepository.findByEmail(email).orElse(null);
		if (member == null) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION);
		}

		member.setNickname(nickname);

		return member;
	}

	@Override
	@Transactional
	public boolean modifyPassword(String email, String password) {
		Member member = memberRepository.findByEmail(email).orElse(null);
		if (member == null) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION);
		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setPassword(passwordEncoder.encode(password));

		return true;
	}

	@Override
	public Member getMember() {
		return memberRepository.findById(Long.valueOf(SecurityUtil.getCurrentUserId().get())).orElse(null);
	}

}
