package com.c211.opinbackend.member.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.c211.opinbackend.auth.jwt.TokenProvider;
import com.c211.opinbackend.auth.model.response.BadgeResponse;
import com.c211.opinbackend.auth.model.response.MypageResponse;
import com.c211.opinbackend.auth.model.response.TechLanguageResponse;
import com.c211.opinbackend.auth.service.MailService;
import com.c211.opinbackend.exception.api.ApiExceptionEnum;
import com.c211.opinbackend.exception.api.ApiRuntimeException;
import com.c211.opinbackend.exception.auth.AuthExceptionEnum;
import com.c211.opinbackend.exception.auth.AuthRuntimeException;
import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.exception.repositroy.RepositoryExceptionEnum;
import com.c211.opinbackend.exception.repositroy.RepositoryRuntimeException;
import com.c211.opinbackend.member.model.dto.MemberDto;
import com.c211.opinbackend.member.model.dto.SimilarDto;
import com.c211.opinbackend.persistence.entity.Badge;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.MemberBadge;
import com.c211.opinbackend.persistence.entity.MemberFollow;
import com.c211.opinbackend.persistence.entity.MemberTechLanguage;
import com.c211.opinbackend.persistence.entity.MemberTopic;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryContributor;
import com.c211.opinbackend.persistence.entity.RepositoryFollow;
import com.c211.opinbackend.persistence.entity.RepositoryPost;
import com.c211.opinbackend.persistence.entity.RepositoryTechLanguage;
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
import com.c211.opinbackend.persistence.repository.RepoTechLanguageRepository;
import com.c211.opinbackend.persistence.repository.RepositoryFollowRepository;
import com.c211.opinbackend.persistence.repository.RepositoryPostMemberLikeRepository;
import com.c211.opinbackend.persistence.repository.TechLanguageRepository;
import com.c211.opinbackend.persistence.repository.TopicRepository;
import com.c211.opinbackend.repo.model.response.RepositoryPostResponse;
import com.c211.opinbackend.repo.model.response.RepositoryResponseDto;
import com.c211.opinbackend.repo.model.response.RepositoryTitleResponse;
import com.c211.opinbackend.repo.model.response.TopicResponse;
import com.c211.opinbackend.repo.service.mapper.RepoMapper;
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
	private final MailService mailService;
	private final MemberFollowRepository memberFollowRepository;
	private final MemberBadgeRepository memberBadgeRepository;
	private final MemberTechLanguageRepository memberTechLanguageRepository;
	private final MemberTopicRepository memberTopicRepository;
	private final BadgeRepository badgeRepository;
	private final RepoTechLanguageRepository repoTechLanguageRepository;
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
	public MemberDto getMemberInfoBySecurityContext() {
		String currLoginEmail = SecurityUtil.getCurrentUserId().orElseThrow(
			() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		Member findMember = memberRepository.findByEmail(currLoginEmail).orElseThrow(
			() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION)
		);

		Authentication user = SecurityContextHolder.getContext().getAuthentication();

		// 그대로 권한 객체를 주기 힘드니 role 일치하는지 검사하고 맞으면 그대로 준다. 아니면 애러 발생
		user.getAuthorities()
			.stream()
			.filter(o -> o.getAuthority().equals(findMember.getRole().toString()))
			.findAny().orElseThrow(
				() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_ACCESS_EXCEPTION)
			);

		return MemberDto.builder()
			.id(findMember.getId())
			.email(findMember.getEmail())
			.nickname(findMember.getNickname())
			.avataUrl(findMember.getAvatarUrl())
			.role(findMember.getRole())
			.githubSync(findMember.isGithubSyncFl())
			.githubId(findMember.getGithubId())
			.build();
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
				.html(repo.getHtmlUrl())
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

	/*
	 * GET 마이페이지 정보
	 * */
	@Override
	public MypageResponse getMemberInfo(String nickname) {
		Member member = memberRepository.findByNickname(nickname)
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));

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

	/*
	 * 닉네임 변경
	 * */
	@Override
	@Transactional
	public Member modifyNickname(String nickname, String email) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		member.setNickname(nickname);
		return member;
	}

	/*
	 * 비밀번호 변경
	 * */
	@Override
	@Transactional
	public boolean modifyPassword(String email, String password) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		member.setPassword(passwordEncoder.encode(password));
		return true;
	}

	/*
	 * GET 현재 로그인된 멤버 정보
	 * */
	@Override
	public Member getMember() {
		String email = SecurityUtil.getCurrentUserId()
			.orElseThrow(() -> new AuthRuntimeException(AuthExceptionEnum.AUTH_SECURITY_AUTHENTICATION_EXCEPTION));
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new AuthRuntimeException(AuthExceptionEnum.AUTH_SECURITY_AUTHENTICATION_EXCEPTION));
		return member;
	}

	/*
	 * 멤버 팔로우
	 * */
	@Override
	public MemberFollow followMember(String nickname) {
		Member fromMember = getMember();
		Member toMember = memberRepository.findByNickname(nickname)
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));

		MemberFollow memberFollow = MemberFollow.builder()
			.fromMember(fromMember)
			.toMember(toMember)
			.build();

		return memberFollowRepository.save(memberFollow);
	}

	/*
	 * 팔로우 취소
	 * */
	@Override
	@Transactional
	public boolean followDeleteMember(String nickname) {
		Member fromMember = getMember();
		Member toMember = memberRepository.findByNickname(nickname)
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));

		MemberFollow follow = memberFollowRepository.findByFromMemberAndToMember(fromMember, toMember).orElse(null);

		follow.setfromMember(null);
		follow.setToMember(null);

		try {
			memberFollowRepository.delete(follow);
		} catch (Exception e) {
			throw new ApiRuntimeException(ApiExceptionEnum.API_CENTER_CALL_EXCEPTION);
		}

		return true;
	}

	/*
	 * 팔로우 여부 확인
	 * */
	@Override
	public boolean isFollow(String nickname) {
		Member fromMember = getMember();
		Member toMember = memberRepository.findByNickname(nickname).orElse(null);

		MemberFollow follow = memberFollowRepository.findByFromMemberAndToMember(fromMember, toMember).orElse(null);

		if (follow == null) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * SAVE 회원가입 시 topic, tech language 저장
	 * */
	@Override
	public boolean saveSignUpTopicAndTechLanguage(String email, List<String> topics, List<String> lans) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		saveTopic(topics, member);
		saveTechLanguage(lans, member);
		return true;
	}

	public void saveTopic(List<String> topics, Member member) {
		for (String topic : topics) {
			Topic isTopic = topicRepository.findByTitle(topic).orElse(null);

			//topic이 있는지 확인
			if (isTopic == null) {
				Topic newTopic = Topic.builder()
					.title(topic)
					.build();

				isTopic = topicRepository.save(newTopic);
			} else {
				// topic 과 member 의 관계가 있는지 확인
				MemberTopic memberTopic = memberTopicRepository.findByMemberAndTopic(member, isTopic).orElse(null);
				if (memberTopic != null) {
					continue;
				}
			}

			//member topic 으로 이어주기
			MemberTopic memberTopic = MemberTopic.builder()
				.topic(isTopic)
				.member(member)
				.build();

			memberTopicRepository.save(memberTopic);
		}
	}

	public void saveTechLanguage(List<String> languages, Member member) {
		for (String lan : languages) {
			TechLanguage language = techLanguageRepository.findByTitle(lan).orElse(null);

			if (language == null) {
				TechLanguage newLanguage = TechLanguage.builder()
					.title(lan)
					.build();

				language = techLanguageRepository.save(newLanguage);
			} else {
				// tech language 과 member 의 관계가 있는지 확인
				MemberTechLanguage memberTechLanguage = memberTechLanguageRepository
					.findByMemberAndTechLanguage(member, language).orElse(null);
				if (memberTechLanguage != null) {
					continue;
				}
			}

			//member tech language 으로 이어주기
			MemberTechLanguage memberTechLanguage = MemberTechLanguage.builder()
				.techLanguage(language)
				.member(member)
				.build();

			memberTechLanguageRepository.save(memberTechLanguage);
		}
	}

	/*
	 * SAVE 로그인 Member Topic
	 * */
	@Override
	public boolean saveLoginTopic(String title) {
		Member member = getMember();
		Topic topic = topicRepository.findByTitle(title).orElse(null);

		if (topic == null) {
			Topic newTopic = Topic.builder()
				.title(title)
				.build();

			topic = topicRepository.save(newTopic);
		}

		MemberTopic memberTopic = MemberTopic.builder()
			.member(member)
			.topic(topic)
			.build();

		memberTopicRepository.save(memberTopic);
		return true;
	}

	/*
	 * SAVE 로그인 Member Tech Language
	 * */
	@Override
	public boolean saveLoginTechLanguage(String title) {
		Member member = getMember();
		TechLanguage techLanguage = techLanguageRepository.findByTitle(title).orElse(null);

		if (techLanguage == null) {
			TechLanguage newLanguage = TechLanguage.builder()
				.title(title)
				.build();

			techLanguage = techLanguageRepository.save(newLanguage);
		}

		MemberTechLanguage memberTechLanguage = MemberTechLanguage.builder()
			.member(member)
			.techLanguage(techLanguage)
			.build();

		memberTechLanguageRepository.save(memberTechLanguage);
		return true;
	}

	/*
	 * GET Tech Language 전체 목록
	 * */
	@Override
	public List<TechLanguageResponse> getListTechLanguage() {
		List<TechLanguage> lanList = techLanguageRepository.findAll();
		List<TechLanguageResponse> responses = new ArrayList<TechLanguageResponse>();

		for (TechLanguage lan : lanList) {
			TechLanguageResponse techLanguageResponse = TechLanguageResponse.builder()
				.id(lan.getId())
				.title(lan.getTitle())
				.build();

			responses.add(techLanguageResponse);
		}

		return responses;
	}

	@Override
	@Transactional
	public boolean deleteLoginMemberTopic(String title) {
		MemberTopic memberTopic = memberTopicRepository.findByMemberAndTopic(getMember(),
				topicRepository.findByTitle(title)
					.orElseThrow(() -> new ApiRuntimeException(ApiExceptionEnum.API_WORK_FAILED_EXCEPTION)))
			.orElseThrow(() -> new ApiRuntimeException(ApiExceptionEnum.API_WORK_FAILED_EXCEPTION));

		memberTopic.setMember(null);
		memberTopic.setTopic(null);
		try {
			memberTopicRepository.delete(memberTopic);
		} catch (Exception e) {
			throw new ApiRuntimeException(ApiExceptionEnum.API_CENTER_CALL_EXCEPTION);
		}
		return true;
	}

	@Override
	@Transactional
	public boolean deleteLoginMemberTechLanguage(String title) {
		MemberTechLanguage memberTechLanguage = memberTechLanguageRepository.findByMemberAndTechLanguage(getMember(),
				techLanguageRepository.findByTitle(title)
					.orElseThrow(() -> new ApiRuntimeException(ApiExceptionEnum.API_WORK_FAILED_EXCEPTION)))
			.orElseThrow(() -> new ApiRuntimeException(ApiExceptionEnum.API_WORK_FAILED_EXCEPTION));

		memberTechLanguage.setMember(null);
		memberTechLanguage.setTechLanguage(null);
		try {
			memberTechLanguageRepository.delete(memberTechLanguage);
		} catch (Exception e) {
			throw new ApiRuntimeException(ApiExceptionEnum.API_CENTER_CALL_EXCEPTION);
		}
		return true;
	}

	@Override
	@Transactional
	public boolean changePwEmail(String email) {
		String pass = mailService.mailSend(email);
		System.out.println(pass);

		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		member.setPassword(passwordEncoder.encode(pass));
		memberRepository.save(member);

		return true;
	}

	@Override
	@Transactional
	public Boolean followRepo(Long repoId, String memberEmail) {
		//맴버 아이디를 가져온다
		Member member = memberRepository.findByEmail(memberEmail).orElseThrow(
			() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION)
		);

		// 팔로우하려는 래포지토리가 내래포 중에는 없어야한다.
		List<Repository> byMemberEmail = repoRepository.findByMemberEmail(memberEmail);
		for (Repository repo : byMemberEmail) {
			if (Objects.equals(repo.getId(), repoId)) {
				throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_FOLLOW_MY_REPO_EXCEPTION);
			}
		}

		// 중복되는 상태를 찾고 없으면 진행한다
		List<RepositoryFollow> findRepoFollow = repositoryFollowRepository.findByRepositoryIdAndMemberId(
			repoId, member.getId()); // 내꺼에 해당되는건지 확인하는 작업
		if (findRepoFollow.size() != 0) { // 없어야 진행된다.
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_FOLLOW_EXIST_EXCEPTION);
		}
		// 래포 아이디를 가져온다.
		Repository repository = repoRepository.findById(repoId).orElseThrow(
			() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_EXIST_EXCEPTION)
		);

		RepositoryFollow createItem = RepositoryFollow.builder()
			.member(member)
			.repository(repository)
			.build();
		RepositoryFollow save = repositoryFollowRepository.save(createItem);
		if (save == null) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_CREATE_FOLLOW_SAVE_EXCEPTION);
		}
		return true;

	}

	@Override
	@Transactional
	public Boolean followDeleteRepo(Long repoId, String memberEmail) {

		Member member = memberRepository.findByEmail(memberEmail).orElseThrow(
			() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION)
		);
		List<RepositoryFollow> findRepoFollowList = repositoryFollowRepository.findByRepositoryIdAndMemberId(
			repoId,
			member.getId());

		// 외부키들을 다 널로 만들고 지워서 캐스케이드 를 방지합니다.
		if (findRepoFollowList.size() == 0) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_FOLLOW_DONT_EXIST_EXCEPTION);
		}
		RepositoryFollow findRepoFollow = findRepoFollowList.get(0);
		findRepoFollow.setNullForeignKey();
		repositoryFollowRepository.delete(findRepoFollow);
		return true;

	}

	@Override
	public Boolean followCheckRepo(Long repoId, String memberEmail) {
		try {
			Member member = memberRepository.findByEmail(memberEmail).orElseThrow(
				() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION)
			);
			List<RepositoryFollow> findRepoFollowList = repositoryFollowRepository.findByRepositoryIdAndMemberId(
				repoId,
				member.getId());
			if (findRepoFollowList.size() == 0) {
				throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_FOLLOW_DONT_EXIST_EXCEPTION);
			}
			RepositoryFollow findRepoFollow = findRepoFollowList.get(0);
			return true;
		} catch (Exception exception) {
			return false;
		}

	}

	@Override
	public List<RepositoryResponseDto> getRecommendRepositories(){
		List<Repository> list = repoRepository.findTop10ByOrderByStargazersCountDesc();
		List<RepositoryResponseDto> starResult = list.stream()
			.map(m-> RepoMapper.toMyRepoDto(m))
			.collect(Collectors.toList());

		Member me = getMember();
		if (me == null) {
			return starResult;
		}

		List<MemberTechLanguage> memberTechs = memberTechLanguageRepository.findByMember(me);
		if (memberTechs == null || memberTechs.size() < 0) {
			return starResult;
		}

		String myTechs = "";
		for (MemberTechLanguage memberTechLanguage : memberTechs) {
			myTechs += memberTechLanguage.getTechLanguage().getTitle();
		}

		List<Repository> targetRepos = findTargetRepositories(me);
		PriorityQueue<SimilarDto> qQueue = new PriorityQueue<>(Collections.reverseOrder());
		for (Repository repo : targetRepos) {
			// 중에서 repo 각각의 언어랑
			List<RepositoryTechLanguage> repoTechRelation = repoTechLanguageRepository.findAllByRepository(repo);

			String repoTechs = "";
			for (RepositoryTechLanguage repositoryTechLanguage : repoTechRelation) {
				repoTechs += repositoryTechLanguage.getTechLanguage().getTitle();
			}
			Double dou = findSimilarity(repoTechs, myTechs);
			// 내 언어 10개랑 비교
			qQueue.offer(new SimilarDto(dou,repo));
		}

		List<RepositoryResponseDto> followResults = new ArrayList<>();
		for(int i = 0, size  = Math.min(10,qQueue.size()); i < size ;i++){
			followResults.add(RepoMapper.toMyRepoDto(qQueue.poll().repo));
		}

		return followResults;
	}

	public List<Repository> findTargetRepositories(Member me) {
		List<Repository> result = new ArrayList<>();

		// 내가 팔로우하고 있는 사람들 최대 10명 중에서 각각이 가지고 있는 레포지토리(팔로우)
		// 스타 수가 가장 많은 레포지토리 10개
		List<MemberFollow> myFollows = memberFollowRepository.findByFromMember(me);
		for (int i = 0; i < Math.min(10,myFollows.size()); i++) {
			List<Repository> toMemberRepos = repoRepository.findByMember(myFollows.get(i).getToMember());
			result.addAll(toMemberRepos);
		}

		List<Repository> findAll = repoRepository.findTop10ByOrderByStargazersCountDesc();
		List<Repository> starDescRepositories = findAll.subList(0, 10);
		result.addAll(starDescRepositories);

		return result;
	}

	public static double findSimilarity(String x, String y) {
		double maxLength = Double.max(x.length(), y.length());
		if (maxLength > 0) {
			// 필요한 경우 선택적으로 대소문자를 무시합니다.
			return (maxLength - StringUtils.getLevenshteinDistance(x, y)) / maxLength;
		}
		return 1.0;
	}
}
