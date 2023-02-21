package dev.opin.opinbackend.repo.service.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.opin.opinbackend.exception.member.MemberExceptionEnum;
import dev.opin.opinbackend.exception.member.MemberRuntimeException;
import dev.opin.opinbackend.exception.repositroy.RepositoryExceptionEnum;
import dev.opin.opinbackend.exception.repositroy.RepositoryRuntimeException;
import dev.opin.opinbackend.persistence.entity.Enterprise;
import dev.opin.opinbackend.persistence.entity.Member;
import dev.opin.opinbackend.persistence.entity.Repository;
import dev.opin.opinbackend.persistence.repository.EnterpriseRepository;
import dev.opin.opinbackend.persistence.repository.MemberRepository;
import dev.opin.opinbackend.persistence.repository.RepoRepository;
import dev.opin.opinbackend.repo.model.dto.RepoDto;
import dev.opin.opinbackend.repo.model.response.RepoDetailResponse;
import dev.opin.opinbackend.repo.model.response.RepositoryResponseDto;
import dev.opin.opinbackend.repo.model.response.RepositoryResponseSimpleDto;
import dev.opin.opinbackend.repo.service.mapper.RepoMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RepositoryServiceImp implements RepositoryService {
	private final RepoRepository repoRepository;
	private final MemberRepository memberRepository;
	private final EnterpriseRepository enterpriseRepository;

	@Override
	@Transactional
	public List<RepositoryResponseDto> finRepositoryListByMember(String email) {
		List<RepositoryResponseDto> repositoryResponseDtoList = new ArrayList<>();
		if (email == null) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION);
		}
		Member findMember = memberRepository.findByEmail(email).orElseThrow(() -> new MemberRuntimeException(
			MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		log.info(findMember.getNickname());
		List<Repository> findMemberRepo = repoRepository.findByMember(findMember);
		for (Repository repo : findMemberRepo) {
			log.info(String.valueOf(repo.getId()));
		}
		if (findMemberRepo.size() == 0) {
			throw new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_EXIST_EXCEPTION);
		}

		for (Repository repo : findMemberRepo) {
			RepositoryResponseDto repositoryResponseDto = RepoMapper.toMyRepoDto(repo);
			repositoryResponseDtoList.add(repositoryResponseDto);
		}
		return repositoryResponseDtoList;
	}

	@Override
	public List<RepositoryResponseSimpleDto> findRepositorySimpleList(Long memberId) {
		List<Repository> findRepos = repoRepository.findAllByMemberId(memberId);

		return findRepos.stream().map(repo -> RepoMapper.toSimepleRepoDto(repo)).collect(Collectors.toList());
	}

	/**
	 * 임시사용되는 컨트롤러 나중에 지워야함
	 *
	 * @param memberEmail
	 * @param repoDto
	 * @return
	 */
	@Override
	@Transactional
	public Boolean uploadRepository(String memberEmail, RepoDto repoDto) {
		// 맴버 없이 래포지토리가 등록 가능해야한다
		Member member = memberRepository.findByEmail(memberEmail)
			.orElseGet(null);
		try {

			Repository repository = RepoMapper.toEntity(member, repoDto);
			log.info(repository.getName());
			repoRepository.save(repository);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return true;
	}

	@Override
	public RepoDetailResponse getDetailResponse(Long repoId) {
		Repository repository = repoRepository.findById(repoId).orElseThrow(
			() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_EXIST_EXCEPTION)
		);

		return RepoMapper.toDetailResponse(repository);
	}

	@Override
	public boolean addEnter(String title) {
		boolean val = true;
		try {
			enterpriseRepository.save(Enterprise.builder().title(title).build());
		}catch (Exception e) {
			val = false;
		}
		return val;
	}
}
