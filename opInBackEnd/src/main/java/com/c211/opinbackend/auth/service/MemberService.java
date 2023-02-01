package com.c211.opinbackend.auth.service;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.auth.model.TokenDto;

public interface MemberService {
	TokenDto authorize(String email, String password);

	Member signUp(MemberDto memberDto) throws Exception;

	boolean existEmail(String email);

	boolean existNickname(String nickname);
}
