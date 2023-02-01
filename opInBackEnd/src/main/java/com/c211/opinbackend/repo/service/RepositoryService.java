package com.c211.opinbackend.repo.service;

import com.c211.opinbackend.auth.model.MemberDto;

public interface RepositoryService {
	MemberDto finRepositoryByMember(String email);
}
