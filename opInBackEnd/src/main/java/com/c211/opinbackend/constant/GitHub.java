package com.c211.opinbackend.constant;

public class GitHub {

	public static final String AUTHORIZE_URL = "https://github.com/login/oauth/authorize";
	public static final String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
	public static final String USER_INFO_URL = "https://api.github.com/user";
	public static final String PUBLIC_USER_INFO_URL = "https://api.github.com/users/{USERNAME}";
	public static final String PUBLIC_USER_REPO_URL = "https://api.github.com/users/{USERNAME}/repos";

	public static String getUserInfoUrl(String githubUserName) {
		return PUBLIC_USER_INFO_URL.replace("{USERNAME}", githubUserName);
	}

	public static String getUserRepoUrl(String githubUserName) {
		return PUBLIC_USER_REPO_URL.replace("{USERNAME}", githubUserName);
	}
}
