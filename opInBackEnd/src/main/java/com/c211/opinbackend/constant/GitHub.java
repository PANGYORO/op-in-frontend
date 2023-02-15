package com.c211.opinbackend.constant;

public class GitHub {

	public static final String AUTHORIZE_URL = "https://github.com/login/oauth/authorize";
	public static final String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
	public static final String USER_INFO_URL = "https://api.github.com/user";
	public static final String PUBLIC_USER_INFO_URL = "https://api.github.com/users/{USERNAME}";
	public static final String PUBLIC_USER_REPO_URL = "https://api.github.com/users/{USERNAME}/repos";

	public static final String PUBLIC_REPOSITORY_LANGUAGE_URL = "https://api.github.com/repos/{REPOSITORY_FULLNAME}/languages";
	public static final String PUBLIC_REPOSITORY_COMMIT_URL = "https://api.github.com/repos/{REPOSITORY_FULLNAME}/commits";

	public static final String PUBLIC_REPOSITORY_PULLS_URL = "https://api.github.com/repos/{REPOSITORY_FULLNAME}/pulls";
	public static final String PUBLIC_REPOSITORY_CONTRIBUTORS_URL = "https://api.github.com/repos/{REPOSITORY_FULLNAME}/contributors";

	public static String getUserInfoUrl(String githubUserName) {
		return PUBLIC_USER_INFO_URL.replace("{USERNAME}", githubUserName);
	}

	public static String getUserRepoUrl(String githubUserName, String page) {
		return PUBLIC_USER_REPO_URL.replace("{USERNAME}", githubUserName)+"?page="+page+"&per_page=100";
	}

	public static String getPublicRepositoryLanguageUrl(String repositoryFullName, String page) {
		return PUBLIC_REPOSITORY_LANGUAGE_URL.replace("{REPOSITORY_FULLNAME}", repositoryFullName)+"?page="+page+"&per_page=100";
	}

	public static String getPublicRepositoryCommitUrl(String repositoryFullName, String page) {
		return PUBLIC_REPOSITORY_COMMIT_URL.replace("{REPOSITORY_FULLNAME}", repositoryFullName)+"?page="+page+"&per_page=100";
	}

	public static String getPublicRepositoryPullsUrl(String repositoryFullName, String page) {
		return PUBLIC_REPOSITORY_PULLS_URL.replace("{REPOSITORY_FULLNAME}", repositoryFullName)+"?page="+page+"&per_page=100";
	}

	public static String getPublicRepositoryContributorsUrl(String repositoryFullName, String page) {
		return PUBLIC_REPOSITORY_CONTRIBUTORS_URL.replace("{REPOSITORY_FULLNAME}", repositoryFullName)+"?page="+page+"&per_page=100";
	}
}
