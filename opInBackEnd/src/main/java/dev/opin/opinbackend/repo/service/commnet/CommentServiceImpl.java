package dev.opin.opinbackend.repo.service.commnet;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.opin.opinbackend.exception.member.MemberExceptionEnum;
import dev.opin.opinbackend.exception.member.MemberRuntimeException;
import dev.opin.opinbackend.exception.repositroy.RepositoryExceptionEnum;
import dev.opin.opinbackend.exception.repositroy.RepositoryRuntimeException;
import dev.opin.opinbackend.persistence.entity.Comment;
import dev.opin.opinbackend.persistence.entity.CommentType;
import dev.opin.opinbackend.persistence.entity.Member;
import dev.opin.opinbackend.persistence.entity.RepositoryPost;
import dev.opin.opinbackend.persistence.entity.RepositoryQnA;
import dev.opin.opinbackend.persistence.repository.CommentRepository;
import dev.opin.opinbackend.persistence.repository.MemberRepository;
import dev.opin.opinbackend.persistence.repository.RepoPostRepository;
import dev.opin.opinbackend.persistence.repository.RepoQnARepository;
import dev.opin.opinbackend.repo.model.requeset.RequestComment;
import dev.opin.opinbackend.repo.model.requeset.RequestCommentCreateToPost;
import dev.opin.opinbackend.repo.model.response.CommentDetailResponse;
import dev.opin.opinbackend.repo.service.mapper.CommentMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
	private final CommentRepository commentRepository;
	private final MemberRepository memberRepository;

	private final RepoPostRepository repoPostRepository;
	private final RepoQnARepository repoQnARepository;

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

	@Override
	@Transactional
	public CommentDetailResponse creatQnAComment(RequestComment requestComment, String email) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		RepositoryQnA repositoryQnA = repoQnARepository.findById(requestComment.getQnaId()).orElseThrow(
			() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_EXIST_EXCEPTION)
		);
		if (requestComment.getComment().isBlank() || requestComment.getComment() == null
			|| requestComment.getComment().length() == 0) {
			throw new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_QNA_CONTENT_EMPTY_EXCEPTION);
		}
		try {
			Comment comment = Comment.builder()
				.commentType(CommentType.QNA)
				.createDate(LocalDateTime.now())
				.updateDate(LocalDateTime.now())
				.content(requestComment.getComment())
				.member(member)
				.repositoryQnA(repositoryQnA)
				.repositoryPost(null)
				.build();
			return CommentMapper.toRepoCommentDetailResponse(commentRepository.save(comment));
		} catch (Exception exception) {
			throw new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_SAVE_EXCEPTION);
		}
	}
}
