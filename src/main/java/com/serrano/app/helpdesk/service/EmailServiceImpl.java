package com.serrano.app.helpdesk.service;

import java.util.Date;
import java.util.Optional;

import com.serrano.app.helpdesk.domain.EmailValidator;
import com.serrano.app.helpdesk.exception.TokenException;
import com.serrano.app.helpdesk.exception.TokenNotFoundException;
import com.serrano.app.helpdesk.repository.EmailRepository;
import com.serrano.app.helpdesk.utils.EmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private EmailRepository emailRepo;
    @Autowired
    private EmailSender emailSender;

    @Override
    public EmailValidator findByToken(String token) {
        //Check if token exist
        Optional<EmailValidator> emailValidator = emailRepo.findByToken(token);
        if(emailValidator.isEmpty()) throw new TokenNotFoundException(token);
        //Check if is confirmed
        if(emailValidator.get().getConfirmed_at() != null) throw new TokenException("Token has been confirmed before");
        //Check if is expired
        boolean expired = emailValidator.get().getExpires_at().before(new Date(System.currentTimeMillis()));
        if(expired) throw new TokenException("Token is expired");
        //Confirm
        emailValidator.get().setConfirmed_at(new Date(System.currentTimeMillis()));
        //Enable user
        emailValidator.get().getUser().setEnabled(true);
        emailValidator.get().getUser().setLocked(false);
        emailRepo.save(emailValidator.get());
        
        return emailValidator.get();
    }

    @Override
    public EmailValidator save(EmailValidator email) {
        EmailValidator emailValidator = emailRepo.save(email);
        //Send email
        emailSender.sendSimpleMessage(emailValidator.getUser().getFirstName(), emailValidator.getToken());
        return emailValidator;
    }
}
