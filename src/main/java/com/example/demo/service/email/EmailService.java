package com.example.demo.service.email;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	
	@Autowired
	private EmailConfiguration configuration;

	@Async
	public void sendMessage(Message poruka) {
		
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		Properties props = sender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
		
		sender.setHost(this.configuration.getHost());
		sender.setPort(this.configuration.getPort());
		sender.setUsername(this.configuration.getUsername());
		sender.setPassword(this.configuration.getPassword());
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(this.configuration.getUsername());
		message.setTo(poruka.getTo());
		message.setSubject(poruka.getTitle());
		message.setText(poruka.getText());
		sender.send(message);
		
	}

}
