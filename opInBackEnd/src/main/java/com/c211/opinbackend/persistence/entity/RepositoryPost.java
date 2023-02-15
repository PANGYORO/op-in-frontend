package com.c211.opinbackend.persistence.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.c211.opinbackend.exception.repositroy.RepositoryExceptionEnum;
import com.c211.opinbackend.exception.repositroy.RepositoryRuntimeException;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@BatchSize(size = 100)
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RepositoryPost {
	@Id
	@GeneratedValue
	@Column(name = "REPOSITORY_POST_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPOSITORY_ID")
	private Repository repository;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member member;

	@OneToMany(mappedBy = "repositoryPost", cascade = CascadeType.ALL)
	List<Comment> commentsList = new ArrayList<>();

	@OneToMany(mappedBy = "repositoryPost", cascade = CascadeType.ALL)
	List<RepositoryPostMemberLike> likeList = new ArrayList<>();

	@Embedded
	private TitleContent titleContent;

	@NotNull
	private Boolean mergeFL;
	@NotNull
	private LocalDateTime date;
	@NotNull
	private Boolean closeState;

	public void createPostToRepo(Repository repository) {
		if (repository != null) {
			this.repository = repository;
		} else {
			throw new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_NULL_EXCEPTION);
		}
	}

	public void fetchTile(String newTitle) {
		if (newTitle.isBlank()) {
			throw new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_POST_TITLE_EMPTY_EXCEPTION);
		}
		this.titleContent.changeTitle(newTitle);
	}

	public void fetchContent(String content) {
		this.titleContent.changeContent(content);
	}
}
