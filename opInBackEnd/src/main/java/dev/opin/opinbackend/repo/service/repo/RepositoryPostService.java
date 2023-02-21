package dev.opin.opinbackend.repo.service.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;

import dev.opin.opinbackend.persistence.entity.RepositoryPost;
import dev.opin.opinbackend.repo.model.requeset.CreatePostRequest;
import dev.opin.opinbackend.repo.model.requeset.RequestUpdatePost;
import dev.opin.opinbackend.repo.model.response.PageResponsePost;
import dev.opin.opinbackend.repo.model.response.RepoPostDetailResponse;
import dev.opin.opinbackend.repo.model.response.RepoPostSimpleResponse;

public interface RepositoryPostService {
	RepositoryPost createPostToRepository(CreatePostRequest createPostRequest, String memberEmail);

	/**
	 * 임시 래포지토리 등록
	 * 래포지토리에 맴버 등록 가능하나 맴버는 널일수도 있다.
	 */

	List<RepoPostSimpleResponse> getRepoAllPostList(Long repoId);

	List<RepoPostSimpleResponse> getAllPost();

	PageResponsePost getNewsPosts(Pageable pageable);

	PageResponsePost getHotPosts(Pageable pageable);

	RepoPostDetailResponse getPostDetail(Long postId);

	Boolean deleteRepo(Long postId);

	Boolean update(RequestUpdatePost post);

	List<RepoPostSimpleResponse> getMembersRepoPost(String nickName);

	Boolean createLike(Long postId);

	Boolean deleteLike(Long postId);

	Boolean checkLike(Long postId);

}
