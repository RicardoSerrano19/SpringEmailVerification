package com.serrano.app.helpdesk.service;

import com.serrano.app.helpdesk.domain.EmailValidator;

public interface EmailService {
    EmailValidator findByToken(String token);
    EmailValidator save(EmailValidator email);
}
