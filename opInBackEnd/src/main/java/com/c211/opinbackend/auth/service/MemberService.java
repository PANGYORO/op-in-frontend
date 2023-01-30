package com.c211.opinbackend.auth.service;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.model.MemberDto;

public interface MemberService {
	Member login(String email, String password);

	Member signUp(MemberDto memberDto);
}
