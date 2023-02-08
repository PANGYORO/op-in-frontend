package com.c211.opinbackend.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryQnA {
	@Id
	@GeneratedValue
	@Column(name = "REPOSITORY_QNA_ID")
	private Long id;

	// TODO: 2023/02/08 qna 삭제되면 래포도 맴버도 삭제되는지 확인 필요합니다
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MEMBER_ID")
	private Member authorMember;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "REPOSITORY_ID")
	private Repository repository;

	private String content;

	private LocalDateTime createTime;

}
