package spring.project.service;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailSendService {
	@Autowired
	private JavaMailSenderImpl mailSender;
	private int authNumber; 
		
		// ���� �߻�(�����е� ���뷯)
		public void makeRandomNumber() {
			// ������ ���� 111111 ~ 999999 (6�ڸ� ����)
			Random r = new Random();
			int checkNum = r.nextInt(888888) + 111111;
			System.out.println("������ȣ : " + checkNum);
			authNumber = checkNum;
		}
		//�̸��� ���� ���! 
		public String joinEmail(String email) {
			makeRandomNumber();
			String setFrom = "�̸��Ͼ���ĭ"; // email-config�� ������ �ڽ��� �̸��� �ּҸ� �Է� 
			String toMail = email;
			String title = "ȸ�� ���� ���� �̸��� �Դϴ�."; // �̸��� ���� 
			String content = 
					"Ȩ�������� �湮���ּż� �����մϴ�." + 	// html �������� �ۼ� ! 
	                "<br><br>" + 
				    "���� ��ȣ�� " + authNumber + "�Դϴ�." + 
				    "<br>" + 
				    "�ش� ������ȣ�� ������ȣ Ȯ�ζ��� �����Ͽ� �ּ���."; //�̸��� ���� ����
			mailSend(setFrom, toMail, title, content);
			return Integer.toString(authNumber);
		}
		//�̸��� ���� �޼ҵ�
		public void mailSend(String setFrom, String toMail, String title, String content) { 
			MimeMessage message = mailSender.createMimeMessage();
			// true �Ű����� �����ϸ� multipart ������ �޼��� ������ ����.���� ���ڵ� ������ �����ϴ�.
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
				helper.setFrom(setFrom);
				helper.setTo(toMail);
				helper.setSubject(title);
				// true ���� > html �������� ���� , �ۼ����� ������ �ܼ� �ؽ�Ʈ�� ����.
				helper.setText(content,true);
				mailSender.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
}