package com.c211.opinbackend.repo.model.repository;

import java.time.LocalDate;
import java.util.List;

import com.c211.opinbackend.repo.model.contributor.RepositoryContributorDto;
import com.c211.opinbackend.repo.model.repoTechLang.RepoTechLangDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RepositoryDto {
	// TODO: 2023-02-02
	/**
	 title: '우리레포는요...',
	 content: '내용이 어쩌구 저쩌고',
	 language: 'python',
	 contributor: [{ nickname: '짱짱맨' },{id:''}, {profileimg:''}],
	 star: 341110,
	 forknum : 321,
	 topic: 'react',
	 updatedate: '2021-01-0

	 */
	private Long id;
	private String title;
	private String content;
	private List<RepoTechLangDto> techLangs;
	private List<RepositoryContributorDto> contributors;
	private List<RepositoryContributorDto> gitContributors;
	private String star;
	private String forkNum;
	private List<String> topicList;
	private LocalDate updateDate;

}
