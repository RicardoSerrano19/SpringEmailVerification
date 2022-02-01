package com.serrano.app.helpdesk.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import com.serrano.app.helpdesk.domain.User;
import com.serrano.app.helpdesk.domain.dto.UserDTO;
import com.serrano.app.helpdesk.exception.EmailNotFoundException;
import com.serrano.app.helpdesk.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDTO save(UserDTO user) {
        User entitySaved = userRepo.save(mapper.map(user, User.class));
        UserDTO userMapped = mapper.map(entitySaved, UserDTO.class);
        return userMapped;
    }

    @Override
    public UserDTO get(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if(!user.isPresent()) throw new EmailNotFoundException(email);
        UserDTO userMapped = mapper.map(user.get(), UserDTO.class);
        return userMapped;
    }

    @Override
    public List<UserDTO> findAll() {
        //List<User> entity = userRepo.findAll();
        List<UserDTO> list = new ArrayList<>();
        return list;
    }
}
