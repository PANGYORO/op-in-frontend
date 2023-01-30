package com.c211.opinbackend.auth.service;

import com.c211.opinbackend.auth.entity.GithubSync;
import com.c211.opinbackend.auth.model.GithubSyncDto;

public interface GithubSyncService {

	GithubSync githubSync(GithubSyncDto githubSyncDto);
}
