package com.c211.opinbackend.search.mapper;

import java.util.stream.Collectors;

import com.c211.opinbackend.persistence.entity.RepositoryQnA;
import com.c211.opinbackend.search.dto.response.MemberDto;
import com.c211.opinbackend.search.dto.response.SearchQnaDto;

public class QnaMapper {

	static public SearchQnaDto toSearchQnaDto(RepositoryQnA repositoryQnA) {
		return SearchQnaDto.builder()
			.id(repositoryQnA.getId())
			.content(repositoryQnA.getContent())
			.createTime(repositoryQnA.getCreateTime())
			.member(MemberDto.from(repositoryQnA.getAuthorMember()))
			.comments(
				repositoryQnA.getComments()
					.stream()
					.map(comment -> CommentMapper.toQnaCommentDto(comment))
					.collect(
						Collectors.toList()
					))
			.build();
	}
}
