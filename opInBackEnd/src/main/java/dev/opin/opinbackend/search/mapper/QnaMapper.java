package dev.opin.opinbackend.search.mapper;

import java.util.stream.Collectors;

import dev.opin.opinbackend.persistence.entity.RepositoryQnA;
import dev.opin.opinbackend.search.dto.response.MemberDto;
import dev.opin.opinbackend.search.dto.response.SearchQnaDto;

public class QnaMapper {

	public static SearchQnaDto toSearchQnaDto(RepositoryQnA repositoryQnA) {
		return SearchQnaDto.builder()
			.id(repositoryQnA.getId())
			.content(repositoryQnA.getContent())
			.createTime(repositoryQnA.getCreateTime())
			.member(MemberDto.from(repositoryQnA.getAuthorMember()))
			.comments(
				repositoryQnA.getComments().stream().map(CommentMapper::toQnaCommentDto).collect(Collectors.toList()))
			.build();
	}
}
