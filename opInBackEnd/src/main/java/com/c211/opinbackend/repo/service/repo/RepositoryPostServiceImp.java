package com.c211.opinbackend.repo.service.repo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.c211.opinbackend.exception.repositroy.RepositoryExceptionEnum;
import com.c211.opinbackend.exception.repositroy.RepositoryRuntimeException;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.Repository;
import com.c211.opinbackend.persistence.entity.RepositoryPost;
import com.c211.opinbackend.persistence.entity.TitleContent;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.persistence.repository.RepoPostRepository;
import com.c211.opinbackend.persistence.repository.RepoRepository;
import com.c211.opinbackend.repo.model.dto.RepoDto;
import com.c211.opinbackend.repo.model.requeset.CreatePostRequest;
import com.c211.opinbackend.repo.model.response.RepoPostSimpleResponse;
import com.c211.opinbackend.repo.service.mapper.RepoMapper;
import com.c211.opinbackend.repo.service.mapper.RepoPostMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RepositoryPostServiceImp implements RepositoryPostService {

	private final MemberRepository memberRepository;
	private final RepoRepository repoRepository;
	private final RepoPostRepository repoPostRepository;

	@Override
	@Transactional
	public Boolean createPostToRepository(CreatePostRequest createPostRequest, String memberEmail) {
		// 등록할 래포지토리를 찾고

		Long repositoryId = createPostRequest.getRepositoryId();
		Repository repository = repoRepository.findById(repositoryId).orElseThrow(
			() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_EXIST_EXCEPTION)
		);
		log.info(repository.getName());
		// 등록하는 맴버를 찾는다.
		Member member = memberRepository.findByEmail(memberEmail)
			.orElseThrow(() -> new RepositoryRuntimeException(RepositoryExceptionEnum.REPOSITORY_EXIST_EXCEPTION));
		// 포스트를 등록한다.
		try {
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
			repoPostRepository.save(repositoryPost);
		} catch (Exception exception) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public Boolean uploadRepository(String memberEmail, RepoDto repoDto) {
		// 맴버 없이 래포지토리가 등록 가능해야한다
		Member member = memberRepository.findByEmail(memberEmail)
			.orElse(null);
		try {

			Repository repository = RepoMapper.toEntity(member, repoDto);
			repoRepository.save(repository);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public List<RepoPostSimpleResponse> getALLPostList() {
		List<RepositoryPost> repositoryPostList = repoPostRepository.findAll();
		List<RepoPostSimpleResponse> mappingResult = new ArrayList<>();
		for (RepositoryPost post : repositoryPostList) {
			mappingResult.add(RepoPostMapper.toSimpleResponse(post));
		}
		return mappingResult;
	}
}
