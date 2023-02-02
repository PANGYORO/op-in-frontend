package com.c211.opinbackend.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.c211.opinbackend.auth.entity.Badge;
import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.entity.MemberBadge;
import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.auth.model.TokenDto;
import com.c211.opinbackend.auth.model.response.BadgeResponse;
import com.c211.opinbackend.auth.model.response.MypageResponse;
import com.c211.opinbackend.auth.repository.BadgeRepository;
import com.c211.opinbackend.auth.repository.MemberBadgeRepository;
import com.c211.opinbackend.auth.repository.MemberFollowRepository;
import com.c211.opinbackend.auth.repository.MemberRepository;
import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.jwt.TokenProvider;
import com.c211.opinbackend.repo.entitiy.Repository;
import com.c211.opinbackend.repo.entitiy.RepositoryPost;
import com.c211.opinbackend.repo.model.response.RepositoryPostResponse;
import com.c211.opinbackend.repo.model.response.RepositoryTitleResponse;
import com.c211.opinbackend.repo.repository.CommentRepository;
import com.c211.opinbackend.repo.repository.RepoPostRepository;
import com.c211.opinbackend.repo.repository.RepoRepository;
import com.c211.opinbackend.repo.repository.RepositoryPostMemberLikeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final TokenProvider tokenProvider;

	MemberRepository memberRepository;

	MemberFollowRepository memberFollowRepository;

	MemberBadgeRepository memberBadgeRepository;

	BadgeRepository badgeRepository;

	RepoRepository repoRepository;

	RepoPostRepository repoPostRepository;

	CommentRepository commentRepository;

	RepositoryPostMemberLikeRepository repositoryPostMemberLikeRepository;

	@Autowired
	public MemberServiceImpl(MemberRepository memberRepository,
		MemberFollowRepository memberFollowRepository,
		MemberBadgeRepository memberBadgeRepository,
		BadgeRepository badgeRepository,
		RepoRepository repoRepository,
		RepoPostRepository repoPostRepository,
		CommentRepository commentRepository,
		RepositoryPostMemberLikeRepository repositoryPostMemberLikeRepository,
		AuthenticationManagerBuilder authenticationManagerBuilder,
		TokenProvider tokenProvider) {
		this.memberRepository = memberRepository;
		this.memberFollowRepository = memberFollowRepository;
		this.memberBadgeRepository = memberBadgeRepository;
		this.repoRepository = repoRepository;
		this.repoPostRepository = repoPostRepository;
		this.repositoryPostMemberLikeRepository = repositoryPostMemberLikeRepository;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.tokenProvider = tokenProvider;
		this.commentRepository = commentRepository;
		this.badgeRepository = badgeRepository;
	}

	@Override
	public TokenDto authorize(String email, String password) {

		UsernamePasswordAuthenticationToken authenticationToken =
			new UsernamePasswordAuthenticationToken(email, password);

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		Member member = memberRepository.findByEmail(authentication.getName()).orElse(null);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String authorities = getAuthorities(authentication);

		return tokenProvider.createToken(member, authorities);
	}

	// 권한 가져오기
	public String getAuthorities(Authentication authentication) {
		return authentication.getAuthorities()
			.stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(","));
	}

	@Override
	public Member signUp(MemberDto memberDto) {

		// 이메일 중복 체크
		boolean existEmail = memberRepository.existsByEmail(memberDto.getEmail());
		if (existEmail) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_EXIST_EMAIL_EXCEPTION);
		}

		// 닉네임 중복 체크
		boolean existNickname = memberRepository.existsByNickname(memberDto.getNickname());
		if (existNickname) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_EXIST_NICKNAME_EXCEPTION);
		}

		Member member = Member.builder()
			.email(memberDto.getEmail())
			.password(memberDto.getPassword())
			.nickname(memberDto.getNickname())
			.githubSyncFl(false)
			.role(memberDto.getRole())
			.build();

		return memberRepository.save(member);
	}

	@Override
	public boolean existEmail(String email) {
		boolean existEmail = memberRepository.existsByEmail(email);
		if (existEmail) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_EXIST_EMAIL_EXCEPTION);
		}
		return existEmail;
	}

	@Override
	public boolean existNickname(String nickname) {
		return memberRepository.existsByNickname(nickname);
	}

	@Override
	public MypageResponse getMemberInfo(String email) {

		Member member = memberRepository.findByEmail(email).orElse(null);
		if (member == null) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION);
		}

		// 사용자의 레포지토리 이름 목록
		List<Repository> myRepos = repoRepository.findByMember(member);
		List<RepositoryTitleResponse> myRepoTitles = new ArrayList<RepositoryTitleResponse>();

		for (Repository myRepo: myRepos) {
			RepositoryTitleResponse repositoryTitleResponse = RepositoryTitleResponse.builder()
				.id(myRepo.getId())
				.title(myRepo.getTitleContent().getTitle())
				.build();

			myRepoTitles.add(repositoryTitleResponse);
		}

		// 사용자가 쓴 포스트 목록
		List<RepositoryPost> myPosts = repoPostRepository.findByMember(member);
		List<RepositoryPostResponse> myRepoPosts = new ArrayList<RepositoryPostResponse>();

		for (RepositoryPost myPost: myPosts) {
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
				.repoTitle(myPost.getRepository().getTitleContent().getTitle())
				.build();

			myRepoPosts.add(repositoryPostResponse);
		}

		List<MemberBadge> memBadRelations = memberBadgeRepository.findByMember(member);
		List<BadgeResponse> myBadges = new ArrayList<BadgeResponse>();
		for (MemberBadge mb : memBadRelations) {
			Badge badge = badgeRepository.findById(mb.getBadge().getId()).orElse(null);

			BadgeResponse badgeResponse = BadgeResponse.builder()
				.title(badge.getTitle())
				.imageUrl(badge.getImageUrl())
				.build();

			myBadges.add(badgeResponse);
		}

		MypageResponse mypageResponse = MypageResponse.builder()
			.nickname(member.getNickname())
			.avataUrl(member.getAvatarUrl())
			.myRepoTitles(myRepoTitles)
			.posts(myRepoPosts)
			.countFollower(memberFollowRepository.countByToMember(member))
			.countFollowing(memberFollowRepository.countByFromMember(member))
			.badges(myBadges)
			.build();

		return mypageResponse;

	}
}
