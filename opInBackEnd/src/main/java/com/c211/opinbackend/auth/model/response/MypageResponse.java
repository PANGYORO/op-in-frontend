package com.c211.opinbackend.auth.model.response;

import java.util.ArrayList;
import java.util.List;

import com.c211.opinbackend.repo.entitiy.Repository;
import com.c211.opinbackend.repo.entitiy.RepositoryPost;
import com.c211.opinbackend.repo.model.response.RepositoryPostResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MypageResponse {

	private String nickname;

	private String avataUrl;

	private RepositoryPostResponse post;

}
