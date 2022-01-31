package com.serrano.app.helpdesk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serrano.app.helpdesk.domain.User;
import com.serrano.app.helpdesk.service.UserServiceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserServiceImpl userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetUsers() throws Exception{
        User user = new User(1L, "firstName", "lastName", "email", "password", false, false);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
            
    }

    @Test
    void saveUser() throws Exception{
        User user = new User(1L, "firstName", "lastName", "ricardo.sslive.com", "password", false, false);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user));
        
            Assertions
            .assertThatThrownBy(
                () -> mvc.perform(request))
            .hasCauseInstanceOf(MethodArgumentNotValidException.class).hasMessageContaining("email must be valid");
    }
    
}
