package com.c211.opinbackend.repo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c211.opinbackend.auth.model.request.MemberEmailRequest;
import com.c211.opinbackend.repo.model.dto.RepoDto;
import com.c211.opinbackend.repo.model.requeset.CreatePostRequest;
import com.c211.opinbackend.repo.model.response.RepositoryResponseDto;
import com.c211.opinbackend.repo.service.repo.RepositoryService;
import com.c211.opinbackend.repo.service.repoTechlag.RepoTechLanguageService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/repo")
@Slf4j
@AllArgsConstructor
public class RepoController {
	private final RepositoryService repositoryService;
	private final RepoTechLanguageService repoTechLanguageService;

	// TODO: 2023-02-07 프론트에게 변경되었다고 알리기
	@PostMapping("/member")
	public ResponseEntity<?> getReposByEmail(@RequestBody MemberEmailRequest emailRequest) throws Exception {
		String email = emailRequest.getEmail();
		List<RepositoryResponseDto> repositoryResponseDtoList = repositoryService.finRepositoryListByMember(email);
		log.info("size:", repositoryResponseDtoList.size());
		for (RepositoryResponseDto dto : repositoryResponseDtoList) {
			log.info(dto.toString());
		}
		return new ResponseEntity<>(repositoryResponseDtoList, HttpStatus.OK);
	}

	/**
	 * 임시 로 레포지토리를 넣는 컨트롤러입니다
	 * 추후 api 조회랑 배치가 돌면 서비스로 대체하겠습니다
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> testPost(@RequestBody RepoDto dto) {
		log.info("input test");
		log.info(dto.toString());
		repositoryService.uploadRepository(dto.getMember().getId(), dto);
		return null;
	}

	@PostMapping("/post")
	public ResponseEntity<?> writePost(@RequestBody CreatePostRequest createPostRequest) {
		log.info(createPostRequest.getContent());

		// TODO: 2023-02-06 이부분 나중에 유틸 들어오면 유틸로 바꿔줘야합니다.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			log.debug("Security Context에 인증정보가 없습니다.");
		}

		String email = null;
		if (authentication.getPrincipal() instanceof UserDetails) {
			UserDetails springSecurityUser = (UserDetails)authentication.getPrincipal();
			System.out.println(springSecurityUser.getAuthorities());
			email = springSecurityUser.getUsername();
		} else if (authentication.getPrincipal() instanceof String) {
			email = (String)authentication.getPrincipal();
		}
		log.info(email);
		return null;
	}

}
