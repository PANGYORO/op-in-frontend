package com.c211.opinbackend.auth.service;

import com.c211.opinbackend.auth.model.MemberDto;
import com.c211.opinbackend.auth.model.TokenDto;
import com.c211.opinbackend.persistence.entity.Member;

public interface AuthService {
	TokenDto authorize(String email, String password);

	Member signUp(MemberDto memberDto);

}
