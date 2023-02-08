package com.c211.opinbackend.repo.service.repo;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.exception.repositroy.RepositoryExceptionEnum;
import com.c211.opinbackend.exception.repositroy.RepositoryRuntimeException;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryQnA;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.persistence.repository.RepoQnARepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.repo.model.requeset.RequestQnA;
import com.c211.opinbackend.repo.model.response.QnAComment;
import com.c211.opinbackend.repo.model.response.RepoQnAResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class RepoQnAServiceImpl implements RepoQnAService {
	private final RepoRepository repoRepository;

	private final RepoQnARepository repoQnARepository;
	private final MemberRepository memberRepository;

	@Override
	public List<RepoQnAResponse> getRepoQnALIst() {
		return null;
	}

	@Override
	public QnAComment getQnAComment() {
		return null;
	}

	@Override
	@Transactional
	public Boolean createRepoQnA(RequestQnA requestQnA, String email) {

		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		Repository repository = repoRepository.findById(requestQnA.getRepoId()).orElseThrow(
			() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_EXIST_EXCEPTION)
		);
		RepositoryQnA repositoryQnA = RepositoryQnA.builder()
			.authorMember(member)
			.repository(repository)
			.content(requestQnA.getComment())
			.createTime(LocalDateTime.now())
			.build();
		repoQnARepository.save(repositoryQnA);
		return null;
	}
}
