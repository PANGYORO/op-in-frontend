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
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryPost;
import com.c211.opinbackend.persistence.entity.TitleContent;
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
			RepositoryResponseDto repositoryResponseDto = RepoMapper.toMyRepoDto(repo);
			repositoryResponseDtoList.add(repositoryResponseDto);
		}
		return repositoryResponseDtoList;
	}

	@Override
	@Transactional
	public Boolean createPostToRepository(Long repositoryId, CreatePostRequest createPostRequest, Long memberId) {
		Repository repository = repoRepository.findById(repositoryId).orElseThrow(
			() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_EXIST_EXCEPTION)
		);

		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_EXIST_EXCEPTION));
		// 래포지토리를 찾아오고 포스트를 래포지토리에 등록한다.
		RepositoryPost repositoryPost = RepositoryPost.builder()
			.repository(repository)
			.member(member)
			.titleContent(TitleContent.builder()
				.content(createPostRequest.getContent())
				.title(createPostRequest.getTitle())
				.build())
			.mergeFL(false) // TODO: 2023-02-07 나중에 api 로 바꿔줘야합니다
			.date(LocalDateTime.now())
			.closeState(false)
			.imageUrl("http://testurl")
			.build();

		repositoryPost.createPostToRepo(repository);

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
