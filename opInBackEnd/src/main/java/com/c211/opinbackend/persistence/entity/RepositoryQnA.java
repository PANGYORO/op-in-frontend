package com.c211.opinbackend.persistence.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MEMBER_ID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member authorMember;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "REPOSITORY_ID")
	private Repository repository;

	@OneToMany(mappedBy = "repositoryQnA")
	private List<Comment> comments = new ArrayList<>();

	@Lob
	private String content;

	private LocalDateTime createTime;

	public void setMemberNull() {
		this.authorMember = null;
	}

	public void setRepositoryNull() {
		this.repository = null;
	}

	public void updateContent(String newContent) {
		this.content = newContent;
	}

}
