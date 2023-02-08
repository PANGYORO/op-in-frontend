package com.c211.opinbackend.repo.service.repo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.exception.repositroy.RepositoryExceptionEnum;
import com.c211.opinbackend.exception.repositroy.RepositoryRuntimeException;
import com.c211.opinbackend.persistence.entity.Comment;
import com.c211.opinbackend.persistence.entity.CommentType;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryQnA;
import com.c211.opinbackend.persistence.repository.CommentRepository;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.persistence.repository.RepoQnARepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.repo.model.requeset.RequestComment;
import com.c211.opinbackend.repo.model.requeset.RequestQnA;
import com.c211.opinbackend.repo.model.response.RepoQnAResponse;
import com.c211.opinbackend.repo.service.mapper.QnaMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class RepoQnAServiceImpl implements RepoQnAService {
	private final CommentRepository commentRepository;
	private final RepoRepository repoRepository;

	private final RepoQnARepository repoQnARepository;
	private final MemberRepository memberRepository;

	@Override
	public List<RepoQnAResponse> getRepoQnALIst(Long repoId) {
		List<RepoQnAResponse> res = new ArrayList<>();
		repoRepository.findById(repoId)
			.orElseThrow(() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_EXIST_EXCEPTION));
		List<RepositoryQnA> qnAList = repoQnARepository.findByRepositoryId(repoId);
		for (RepositoryQnA repoQnA : qnAList) {
			List<Comment> byRepositoryQnAId = commentRepository.findByRepositoryQnAId(repoQnA.getId());
			res.add(QnaMapper.entityToResponseQnA(repoQnA, byRepositoryQnAId));
		}
		return res;
	}

	@Override
	@Transactional
	public Boolean createRepoQnA(RequestQnA requestQnA, String email) {

		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		Repository repository = repoRepository.findById(requestQnA.getRepoId()).orElseThrow(
			() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_EXIST_EXCEPTION)
		);
		if (requestQnA.getComment().isBlank() || requestQnA.getComment() == null
			|| requestQnA.getComment().length() == 0) {
			throw new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_QNA_CONTENT_EMPTY_EXCEPTION);
		}
		try {

			RepositoryQnA repositoryQnA = RepositoryQnA.builder()
				.authorMember(member)
				.repository(repository)
				.content(requestQnA.getComment())
				.createTime(LocalDateTime.now())
				.build();
			repoQnARepository.save(repositoryQnA);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean creatQnAComment(RequestComment requestComment, String email) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		RepositoryQnA repositoryQnA = repoQnARepository.findById(requestComment.getQnaId()).orElseThrow(
			() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_QNA_COMMENT_EMPTY_EXCEPTION)
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
			commentRepository.save(comment);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
