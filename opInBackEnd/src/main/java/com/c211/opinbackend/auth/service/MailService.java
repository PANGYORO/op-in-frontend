package com.c211.opinbackend.auth.service;

import java.util.Random;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.c211.opinbackend.exception.api.ApiExceptionEnum;
import com.c211.opinbackend.exception.api.ApiRuntimeException;
import com.c211.opinbackend.exception.member.MemberExceptionEnum;
import com.c211.opinbackend.exception.member.MemberRuntimeException;
import com.c211.opinbackend.persistence.entity.Member;
import com.c211.opinbackend.persistence.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender javaMailSender;

	MemberRepository memberRepository;

	public String mailSend(String email) {

		Member member = memberRepository.findByEmail(email).orElse(null);
		if (member == null) {
			throw new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION);
		}

		String temporaryPassword = createCode();

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("Op-in 임시 비밀번호 발급 안내");
		simpleMailMessage.setText(" Op-in 임시 비밀번호 발급 안내 \n " + temporaryPassword);

		try {
			javaMailSender.send(simpleMailMessage);
		} catch (MailException ex) {
			throw new ApiRuntimeException(ApiExceptionEnum.API_CENTER_CALL_EXCEPTION);
		}

		return temporaryPassword;
	}

	public String createCode() {
		Random random = new Random();
		StringBuffer key = new StringBuffer();

		for (int i = 0; i < 8; i++) {
			int index = random.nextInt(4);

			switch (index) {
				case 0:
					key.append((char)((int)random.nextInt(26) + 97));
					break;
				case 1:
					key.append((char)((int)random.nextInt(26) + 65));
					break;
				default:
					key.append(random.nextInt(9));
			}
		}

		// #?!@$%^&*-
		char pwCollection[] = new char[] {'#', '?', '!', '@', '$', '%', '^', '&', '*', '-'};
		key.append(pwCollection[(int)random.nextInt(10)]);

		return key.toString();
	}

}
