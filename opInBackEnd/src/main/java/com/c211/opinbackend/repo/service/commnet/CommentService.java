package com.c211.opinbackend.repo.service.commnet;

import com.c211.opinbackend.persistence.entity.Comment;
import com.c211.opinbackend.repo.model.requeset.RequestCommentCreateToPost;

public interface CommentService {
	Comment createCommentToPost(String memberEmail, RequestCommentCreateToPost request);
}
