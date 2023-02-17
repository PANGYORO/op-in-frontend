package com.c211.opinbackend.repo.service.commnet;

import com.c211.opinbackend.persistence.entity.Comment;
import com.c211.opinbackend.repo.model.requeset.RequestComment;
import com.c211.opinbackend.repo.model.requeset.RequestCommentCreateToPost;
import com.c211.opinbackend.repo.model.response.CommentDetailResponse;

public interface CommentService {
	Comment createCommentToPost(String memberEmail, RequestCommentCreateToPost request);

	CommentDetailResponse creatQnAComment(RequestComment requestComment, String email);

}
