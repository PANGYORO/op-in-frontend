package com.c211.opinbackend.repo.service.repo;

import java.util.List;

import com.c211.opinbackend.persistence.entity.RepositoryPost;
import com.c211.opinbackend.repo.model.requeset.CreatePostRequest;
import com.c211.opinbackend.repo.model.requeset.RequestUpdatePost;
import com.c211.opinbackend.repo.model.response.RepoPostDetailResponse;
import com.c211.opinbackend.repo.model.response.RepoPostSimpleResponse;

public interface RepositoryPostService {
	RepositoryPost createPostToRepository(CreatePostRequest createPostRequest, String memberEmail);

	/**
	 * 임시 래포지토리 등록
	 * 래포지토리에 맴버 등록 가능하나 맴버는 널일수도 있다.
	 */

	List<RepoPostSimpleResponse> getRepoAllPostList(Long repoId);

	List<RepoPostSimpleResponse> getAllPost();

	RepoPostDetailResponse getPostDetail(Long postId);

	Boolean deleteRepo(Long postId);

	Boolean update(RequestUpdatePost post);

	List<RepoPostSimpleResponse> getMembersRepoPost(String nickName);

	Boolean createLike(Long postId);

	Boolean deleteLike(Long postId);

	Boolean checkLike(Long postId);

}
