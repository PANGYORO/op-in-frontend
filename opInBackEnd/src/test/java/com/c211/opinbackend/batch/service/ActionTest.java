package com.c211.opinbackend.batch.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
	public void REPOSITORY_API_REQUEST_TEST() {
		RepositoryDto[] repoDtos = action.getMemberRepository("", "Djunnni");
		for (RepositoryDto repo : repoDtos) {
			System.out.println(repo);
		}
	}

	@Test
	public void USER_INFO_API_REQUEST_TEST() {
		UserDto userDto = action.getMemberInfo("Djunnni");
		System.out.println(userDto);
	}
}
