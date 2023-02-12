package com.c211.opinbackend.batch.item.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.c211.opinbackend.batch.dto.github.ContributorDto;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.entity.RepositoryContributor;
import com.c211.opinbackend.persistence.repository.MemberRepository;
import com.c211.opinbackend.persistence.repository.RepoContributorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetRepoContributorWriter implements ItemWriter<ContributorDto> {

	private final MemberRepository memberRepository;
	private final RepoContributorRepository repoContributorRepository;


	@Override
	public void write(List<? extends ContributorDto> items) throws Exception {
		for (ContributorDto contributor : items) {
			try {
				Member member = memberRepository.findByGithubId(contributor.getId().toString()).orElse(null);
				if (member != null) {
					RepositoryContributor con = RepositoryContributor
						.builder()
						.repository(contributor.getRepository())
						.member(member)
						.build();

					repoContributorRepository.save(con);
				}
			} catch (Exception e) {
				log.info(e.toString());
			}
		}
	}
}
