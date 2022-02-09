package com.serrano.app.helpdesk.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    
    private final String BASE_URL = "http://localhost:8080/api/v1";
    
    @Autowired
    private JavaMailSender emailSender;

    @Async
    public void sendSimpleMessage(String name, String token) {
        try{
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(EmailBuilder.build(name, BASE_URL + "/emails?token=" + token), true);
            helper.setTo("javierarriaga1906@gmail.com");
            helper.setSubject("Confirm your email");
            helper.setFrom("javierarriaga1906@gmail.com");
            emailSender.send(mimeMessage);
        }catch(MessagingException ex){
            throw new IllegalStateException(ex);
        }
    }
}
