package dev.opin.opinbackend.member.service;

import java.util.List;

import dev.opin.opinbackend.auth.model.response.MypageResponse;
import dev.opin.opinbackend.auth.model.response.TechLanguageResponse;
import dev.opin.opinbackend.member.model.dto.MemberDto;
import dev.opin.opinbackend.persistence.entity.Member;
import dev.opin.opinbackend.persistence.entity.MemberFollow;
import dev.opin.opinbackend.repo.model.response.RepositoryResponseDto;

public interface MemberService {

	MemberDto getMemberInfoBySecurityContext();

	MypageResponse getMemberInfo(String email);

	boolean existEmail(String email);

	boolean existNickname(String nickname);

	boolean isOAuthMember(String email);

	Member modifyNickname(String nickname, String email);

	boolean modifyPassword(String email, String password);

	boolean deleteMember(String email, String password);

	boolean deleteGithubMember(String email);

	Member getMember();

	MemberFollow followMember(String nickname);

	boolean followDeleteMember(String nickname);

	boolean isFollow(String nickname);

	boolean saveSignUpTopicAndTechLanguage(String email, List<String> topics, List<String> languages);

	boolean saveLoginTopic(String title);

	boolean saveLoginTechLanguage(String title);

	List<TechLanguageResponse> getListTechLanguage();

	boolean deleteLoginMemberTopic(String title);

	boolean deleteLoginMemberTechLanguage(String title);

	Boolean followRepo(Long repoId, String memberEmail);

	Boolean followDeleteRepo(Long repoId, String memberEmail);

	Boolean followCheckRepo(Long repoId, String memberEmail);

	boolean changePwEmail(String email);

	List<RepositoryResponseDto> getRecommendRepositories();
}
