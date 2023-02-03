package com.c211.opinbackend.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;

import java.util.Random;

import com.c211.opinbackend.auth.entity.Member;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender javaMailSender;
	private final MemberService memberService;

	public String sendMail(String email) {
		log.info("들어옴~!!!!");
		log.info(email);

		String authNum = createCode();

		log.info(authNum);

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email);
			message.setFrom("krocd@naver.com");
			message.setSubject("Op-in 임시 비밀번호 발급 안내");
			message.setText(authNum);
			javaMailSender.send(message);

			log.info("Success");

			return authNum;

		} catch (Exception e) {
			log.info("fail");
			throw new RuntimeException(e);
		}
	}

	// 인증번호 및 임시 비밀번호 생성 메서드
	public String createCode() {
		Random random = new Random();
		StringBuffer key = new StringBuffer();

		for (int i = 0; i < 8; i++) {
			int index = random.nextInt(4);

			switch (index) {
				case 0: key.append((char) ((int) random.nextInt(26) + 97)); break;
				case 1: key.append((char) ((int) random.nextInt(26) + 65)); break;
				default: key.append(random.nextInt(9));
			}
		}
		return key.toString();
	}

}
