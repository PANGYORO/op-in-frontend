package dev.opin.opinbackend.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.opin.opinbackend.auth.model.PrincipalDetails;
import dev.opin.opinbackend.exception.member.MemberExceptionEnum;
import dev.opin.opinbackend.exception.member.MemberRuntimeException;
import dev.opin.opinbackend.persistence.entity.Member;
import dev.opin.opinbackend.persistence.repository.MemberRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	private final MemberRepository memberRepository;

	public CustomUserDetailService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(username)
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));

		return new PrincipalDetails(member);
	}
}
