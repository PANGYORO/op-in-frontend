package com.c211.opinbackend.search.mapper;

import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.search.dto.response.MemberDto;

public class MemberMapper {
	public static MemberDto toMemberDto(Member member, boolean follow) {
		return MemberDto
			.builder()
			.id(member.getId())
			.nickname(member.getNickname())
			.avatarUrl(member.getAvatarUrl())
			.follow(follow)
			.build();
	}


}
