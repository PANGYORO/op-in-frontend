package com.c211.opinbackend.search.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.repository.MemberFollowRepository;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.search.dto.response.MemberDto;
import com.c211.opinbackend.search.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	final MemberRepository memberRepository;
	final MemberFollowRepository memberFollowRepository;

	public List<MemberDto> search(String keyword) {
		return search(null, keyword);
	}

	public List<MemberDto> search(String requestMemberEmail, String keyword) {
		log.info("keyword: {} , userEmail: {}", keyword, requestMemberEmail);
		List<Member> memberList;
		if (keyword == null || keyword.isEmpty()) {
			memberList = memberRepository.findAll();
		} else {
			memberList = memberRepository.findAllByNicknameContaining(keyword);
		}

		Member requestMember = memberRepository.findByEmail(requestMemberEmail).orElse(null);

		// 요청 유저가 있다면!
		if (requestMember != null) {
			return memberList.stream().map(member -> {
				boolean follow = memberFollowRepository.existsByFromMemberAndToMember(requestMember, member);
				return MemberMapper.toMemberDto(member, follow);
			}).filter(m -> requestMember.getId() != m.getId()).collect(Collectors.toList());
		} else {
			// 요청 유저가 없다면!
			return memberList.stream()
				.map(member -> MemberMapper.toMemberDto(member, false))
				.collect(Collectors.toList());
		}
	}

	public List<MemberDto> search(String requestMemberEmail, String keyword, Pageable pageable) {
		log.info("keyword: {} , userEmail: {}, pageable: {}", keyword, requestMemberEmail, pageable);
		List<Member> memberList;
		if (keyword == null || keyword.isEmpty()) {
			memberList = memberRepository.findAll(pageable).getContent();
		} else {
			memberList = memberRepository.findAllByNicknameContaining(keyword, pageable).getContent();
		}

		Member requestMember = memberRepository.findByEmail(requestMemberEmail).orElse(null);

		// 요청 유저가 있다면!
		if (requestMember != null) {
			return memberList.stream().map(member -> {
				boolean follow = memberFollowRepository.existsByFromMemberAndToMember(requestMember, member);
				return MemberMapper.toMemberDto(member, follow);
			}).filter(m -> requestMember.getId() != m.getId()).collect(Collectors.toList());
		} else {
			// 요청 유저가 없다면!
			return memberList.stream()
				.map(member -> MemberMapper.toMemberDto(member, false))
				.collect(Collectors.toList());
		}
	}
}
