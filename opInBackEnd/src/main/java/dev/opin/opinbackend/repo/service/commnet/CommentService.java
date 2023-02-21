package dev.opin.opinbackend.repo.service.commnet;

import dev.opin.opinbackend.persistence.entity.Comment;
import dev.opin.opinbackend.repo.model.requeset.RequestComment;
import dev.opin.opinbackend.repo.model.requeset.RequestCommentCreateToPost;
import dev.opin.opinbackend.repo.model.response.CommentDetailResponse;

public interface CommentService {
	Comment createCommentToPost(String memberEmail, RequestCommentCreateToPost request);

	CommentDetailResponse creatQnAComment(RequestComment requestComment, String email);

}
