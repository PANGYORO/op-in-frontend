package com.c211.opinbackend.repo.service.commnet;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.exception.repositroy.RepositoryExceptionEnum;
import com.c211.opinbackend.exception.repositroy.RepositoryRuntimeException;
import com.c211.opinbackend.persistence.entity.Comment;
import com.c211.opinbackend.persistence.entity.CommentType;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.RepositoryPost;
import com.c211.opinbackend.persistence.repository.CommentRepository;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.persistence.repository.RepoPostRepository;
import com.c211.opinbackend.repo.model.requeset.RequestCommentCreateToPost;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
	private final CommentRepository commentRepository;
	private final MemberRepository memberRepository;

	private final RepoPostRepository repoPostRepository;

	@Override
	@Transactional
	public Comment createCommentToPost(String memberEmail, RequestCommentCreateToPost request) {
		Member member = memberRepository.findByEmail(memberEmail)
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		RepositoryPost repositoryPost = repoPostRepository.findById(request.getPostId())
			.orElseThrow(() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_POST_EXIST_EXCEPTION));
		try {
			Comment commentToCreate = Comment.builder()
				.member(member)
				.commentType(CommentType.POST)
				.repositoryQnA(null)
				.repositoryPost(repositoryPost)
				.content(request.getCommentContent())
				.createDate(LocalDateTime.now())
				.updateDate(LocalDateTime.now())
				.build();
			Comment comment = commentRepository.save(commentToCreate);
			return comment;
		} catch (Exception e) {
			throw new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_POST_COMMENT_SAVE_EXCEPTION);
		}
	}
}
