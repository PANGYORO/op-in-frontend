package com.c211.opinbackend.repo.service.commnet;

import com.c211.opinbackend.repo.model.requeset.RequestCommentCreateToPost;

public interface CommentService {
	Boolean createCommentToPost(String memberEmail, RequestCommentCreateToPost request);
}
