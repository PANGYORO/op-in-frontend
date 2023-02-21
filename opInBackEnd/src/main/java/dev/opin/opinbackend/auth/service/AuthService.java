package dev.opin.opinbackend.auth.service;

import dev.opin.opinbackend.auth.model.MemberDto;
import dev.opin.opinbackend.auth.model.TokenDto;
import dev.opin.opinbackend.persistence.entity.Member;

public interface AuthService {
	TokenDto authorize(String email, String password);

	Member signUp(MemberDto memberDto);

}
