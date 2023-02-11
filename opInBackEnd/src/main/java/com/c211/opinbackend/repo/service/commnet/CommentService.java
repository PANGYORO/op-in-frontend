package com.c211.opinbackend.repo.service.commnet;

import com.c211.opinbackend.persistence.entity.Comment;
import com.c211.opinbackend.repo.model.requeset.RequestComment;
import com.c211.opinbackend.repo.model.requeset.RequestCommentCreateToPost;
import com.c211.opinbackend.repo.model.response.RepoQnAResponse;

public interface CommentService {
	Comment createCommentToPost(String memberEmail, RequestCommentCreateToPost request);

	RepoQnAResponse creatQnAComment(RequestComment requestCommnet, String email);

}
