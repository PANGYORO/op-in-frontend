package com.c211.opinbackend.repo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.c211.opinbackend.auth.entity.Member;
import com.c211.opinbackend.auth.repository.MemberRepository;
import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.exception.repositroy.RepositoryExceptionEnum;
import com.c211.opinbackend.exception.repositroy.RepositoryRuntimeException;
import com.c211.opinbackend.repo.entitiy.Repository;
import com.c211.opinbackend.repo.model.repository.RepositoryDto;
import com.c211.opinbackend.repo.repository.RepoRepository;
import com.c211.opinbackend.repo.service.mapper.RepoMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RepositoryServiceImp implements RepositoryService {
	private final RepoRepository repoRepository;
	private final MemberRepository memberRepository;

	@Override
	public List<RepositoryDto> finRepositoryListByMember(String email) {
		List<RepositoryDto> repositoryDtoList = new ArrayList<>();
		
		if (email == null)
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION);
		Member findMember = memberRepository.findByEmail(email).orElseThrow(() -> new MemberRuntimeException(
			MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		List<Repository> findMemberRepo = repoRepository.findByMember(findMember);

		if (findMemberRepo.size() == 0)
			throw new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_EXIST_EXCEPTION);

		for (Repository repo : findMemberRepo) {
			RepositoryDto repositoryDto = RepoMapper.toDto(repo);
			repositoryDtoList.add(repositoryDto);
		}
		return repositoryDtoList;
	}
}
