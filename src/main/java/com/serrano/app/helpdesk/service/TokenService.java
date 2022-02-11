package com.serrano.app.helpdesk.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TokenService {
    void refresh(HttpServletRequest request, HttpServletResponse response);
}
