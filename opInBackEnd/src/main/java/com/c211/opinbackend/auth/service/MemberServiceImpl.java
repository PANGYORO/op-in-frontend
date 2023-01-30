package com.c211.opinbackend.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.auth.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	MemberRepository memberRepository;
	@Autowired
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public Member login(String email, String password) {
		Optional<Member> memberOptional = memberRepository.findByEmailAndPassword(email, password);
		return memberOptional.orElse(null);
	}

	@Override
	public Member signUp(MemberDto memberDto) {

		Member member = Member.builder()
			.email(memberDto.getEmail())
			.password(memberDto.getPassword())
			.nickname(memberDto.getNickname())
			.githubSyncFl(false)
			.build();

		return memberRepository.save(member);
	}
}
