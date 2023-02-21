package dev.opin.opinbackend.search.mapper;

import dev.opin.opinbackend.persistence.entity.Member;
import dev.opin.opinbackend.search.dto.response.MemberDto;

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
