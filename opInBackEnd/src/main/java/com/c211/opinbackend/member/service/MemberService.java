package com.c211.opinbackend.member.service;

import java.util.Optional;

import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.auth.model.TokenDto;
import com.c211.opinbackend.auth.model.response.MypageResponse;
import com.c211.opinbackend.persistence.entity.Member;

public interface MemberService {

	Optional<Member> findByEmail(String email);

	MypageResponse getMemberInfo(String email);

	boolean existEmail(String email);

	boolean existNickname(String nickname);

	boolean isOAuthMember(String email);

	Member modifyNickname(String nickname, String email);

	boolean modifyPassword(String email, String password);

	boolean deleteMember(String email, String password);

	boolean deleteGithubMember(String email);

	Member getMember();

}
