package dev.opin.opinbackend.search.dto.response;

import dev.opin.opinbackend.persistence.entity.Member;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MemberDto {
	private Long id;
	private String nickname;

	@JsonProperty("user_img")
	private String avatarUrl;

	private boolean follow;

	public static MemberDto from(Member member) {
		return MemberDto.builder()
			.id(member.getId())
			.nickname(member.getNickname())
			.avatarUrl(member.getAvatarUrl())
			.build();
	}
}
