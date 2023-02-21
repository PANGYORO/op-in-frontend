package dev.opin.opinbackend.auth.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import dev.opin.opinbackend.exception.api.ApiExceptionEnum;
import dev.opin.opinbackend.exception.api.ApiRuntimeException;
import dev.opin.opinbackend.exception.member.MemberExceptionEnum;
import dev.opin.opinbackend.exception.member.MemberRuntimeException;
import dev.opin.opinbackend.persistence.entity.Member;
import dev.opin.opinbackend.persistence.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

	private final JavaMailSender javaMailSender;
	private final SpringTemplateEngine templateEngine;

	private final MemberRepository memberRepository;

	public String mailSend(String email) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberRuntimeException(MemberExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION));
		String temporaryPassword = createCode();

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("Op-in 임시 비밀번호 발급 안내");
		simpleMailMessage.setText("Op-in 임시 비밀번호 발급 안내 \n " + temporaryPassword);
		String mimeMessage = "Op-in 임시 비밀번호 발급 안내";

		try {
			sendTemplateMessage("Op-in 임시 비밀번호 발급 안내", member.getEmail(), temporaryPassword);

		} catch (MessagingException ex) {
			log.info("메일 발송 실패");
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
		char[] pwCollection = new char[] {'#', '?', '!', '@', '$', '%', '^', '&', '*', '-'};
		key.append(pwCollection[(int)random.nextInt(10)]);

		return key.toString();
	}

	public void sendTemplateMessage(String title, String address, String token) throws
		MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		//메일 제목 설정
		helper.setSubject(title);

		//수신자 설정
		helper.setTo(address);

		//템플릿에 전달할 데이터 설정
		Map<String, String> emailValues = new HashMap<>();
		emailValues.put("code", token);

		Context context = new Context();
		emailValues.forEach(context::setVariable);

		//메일 내용 설정 : 템플릿 프로세스
		String html = templateEngine.process("email", context);
		helper.setText(html, true);
		//메일 보내기
		javaMailSender.send(message);
	}

}
