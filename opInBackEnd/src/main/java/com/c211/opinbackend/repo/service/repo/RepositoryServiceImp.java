package com.c211.opinbackend.repo.service.repo;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.exception.repositroy.RepositoryExceptionEnum;
import com.c211.opinbackend.exception.repositroy.RepositoryRuntimeException;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.repo.model.dto.RepoDto;
import com.c211.opinbackend.repo.model.requeset.CreatePostRequest;
import com.c211.opinbackend.repo.model.response.RepositoryResponseDto;
import com.c211.opinbackend.repo.service.mapper.RepoMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RepositoryServiceImp implements RepositoryService {
	private final RepoRepository repoRepository;
	private final MemberRepository memberRepository;

	@Override
	@Transactional
	public List<RepositoryResponseDto> finRepositoryListByMember(String email) {
		List<RepositoryResponseDto> repositoryResponseDtoList = new ArrayList<>();
		if (email == null)
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION);
		Member findMember = memberRepository.findByEmail(email).orElseThrow(() -> new MemberRuntimeException(
			MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		log.info(findMember.getNickname());
		List<Repository> findMemberRepo = repoRepository.findByMember(findMember);
		for (Repository repo : findMemberRepo) {
			log.info(String.valueOf(repo.getId()));
		}
		if (findMemberRepo.size() == 0)
			throw new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_EXIST_EXCEPTION);

		for (Repository repo : findMemberRepo) {
			RepositoryResponseDto repositoryResponseDto = RepoMapper.toDto(repo);
			repositoryResponseDtoList.add(repositoryResponseDto);
		}
		return repositoryResponseDtoList;
	}

	@Override
	public Boolean createPostToRepository(Long repositoryId, CreatePostRequest createPostRequest, String email) {
		// TODO: 2023/02/06 래포지토리 아이디를 가지고 래포지토리를 에 포스트를 등록해야한다
		return true;

	}

	@Override
	@Transactional
	public Boolean uploadRepository(String memberEmail, RepoDto repoDto) {
		Member member = memberRepository.findByEmail(memberEmail)
			.orElse(null);
		// 맴버 없이 래포지토리가 등록 가능해야한다
		try {

			Repository repository = RepoMapper.toEntity(member, repoDto);
			repoRepository.save(repository);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

}
