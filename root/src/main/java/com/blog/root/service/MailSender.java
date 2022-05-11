package blog.root.service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

import blog.root.model.MailDTO;

public interface MailSender {
	
 void send(MailDTO maildto) throws MailException;
 //void send(SimpleMailMessage simpleMessages) throws MailException;
 
 //void send(SimpleMailMessage[] simpleMessages) throws MailException;
}
