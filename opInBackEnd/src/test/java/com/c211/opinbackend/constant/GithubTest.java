package com.c211.opinbackend.constant;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Github {
	@Test
	public void USER_REPOSITORY_URL_TEST() {
		assertEquals("https://api.github.com/users/Djunnni/repos", GitHub.getUserRepoUrl("Djunnni"));
	}
}
