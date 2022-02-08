package com.serrano.app.helpdesk.service;

import java.util.Optional;

import com.serrano.app.helpdesk.domain.EmailValidator;
import com.serrano.app.helpdesk.exception.TokenNotFoundException;
import com.serrano.app.helpdesk.repository.EmailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private EmailRepository emailRepo;

    @Override
    public EmailValidator findByToken(String token) {
        Optional<EmailValidator> emailValidator = emailRepo.findByToken(token);
        if(emailValidator.isEmpty()) throw new TokenNotFoundException(token);
        return emailValidator.get();
    }

    @Override
    public EmailValidator save(EmailValidator email) {
        EmailValidator emailValidator = emailRepo.save(email);
        return emailValidator;
    }
}
