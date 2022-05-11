package blog.root.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import blog.root.model.MailDTO;

@Service
public class MailSenderService implements MailSender {

	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Override
	public void send(MailDTO maildto) throws MailException {
		
		final MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
				helper.setFrom("kjh22522252@gmail.com");
				helper.setTo(maildto.getEmail());
				helper.setText(" 인증 번호 : ["+maildto.getCertification() +"] ",true);
				helper.setSubject("KangBlog 회원 가입 이메일 인증 번호 ");
				
			}
		};
		
		mailSender.send(preparator);
	}
	

}
