package com.c211.opinbackend.auth.service;

import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.c211.opinbackend.exception.api.ApiExceptionEnum;
import com.c211.opinbackend.exception.api.ApiRuntimeException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender javaMailSender;

	public String mailSend(String email) {
		String temporaryPassword = createCode();

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("Op-in 임시 비밀번호 발급 안내");
		simpleMailMessage.setText(" Op-in 임시 비밀번호 발급 안내 \n "+temporaryPassword);

		try {
			javaMailSender.send(simpleMailMessage);
		} catch (Exception ex) {
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
				case 0: key.append((char) ((int) random.nextInt(26) + 97)); break;
				case 1: key.append((char) ((int) random.nextInt(26) + 65)); break;
				default: key.append(random.nextInt(9));
			}
		}

		// #?!@$%^&*-
		char pwCollection[] = new char[] {'#','?','!','@','$','%','^','&','*','-' };
		key.append(pwCollection[(int) random.nextInt(10)]);

		return key.toString();
	}

}
