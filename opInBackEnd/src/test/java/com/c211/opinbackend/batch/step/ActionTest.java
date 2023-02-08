package com.c211.opinbackend.batch.step;

import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.c211.opinbackend.batch.dto.github.CommitDto;
import com.c211.opinbackend.batch.dto.github.RepositoryDto;
import com.c211.opinbackend.batch.dto.github.UserDto;

@SpringBootTest
public class ActionTest {
	Action action;

	@Autowired
	public ActionTest(Action action) {
		this.action = action;
	}

	@Test
	public void USER_REPOSITORIES_REQUEST_TEST() {
		RepositoryDto[] repoDtos = action.getMemberRepository("", "Djunnni");
		for (RepositoryDto repo : repoDtos) {
			System.out.println(repo);
		}
	}

	@Test
	public void REPOSITORY_COMMIT_REQUEST_TEST() {
		CommitDto[] commits = action.getRepositoryCommits("Djunnni/Algorithm");

		System.out.println(Arrays.toString(commits));
	}

	@Test
	public void REPOSITORY_LANGUAGE_REQUEST_TEST() {
		Map<String, Long> languages = action.getRepositoryLanguages("Djunnni/Algorithm");
		System.out.println(languages);
	}

	@Test
	public void USER_INFO_REQUEST_TEST() {
		UserDto userDto = action.getMemberInfo("Djunnni");
		System.out.println(userDto);
	}
}
