package com.serrano.app.helpdesk.service;

import java.util.List;
import java.util.Optional;
import com.serrano.app.helpdesk.domain.User;
import com.serrano.app.helpdesk.domain.dto.UserDTO;
import com.serrano.app.helpdesk.domain.dto.UserRoleDTO;
import com.serrano.app.helpdesk.exception.EmailDuplicatedException;
import com.serrano.app.helpdesk.exception.EmailNotFoundException;
import com.serrano.app.helpdesk.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
        //Find if email is taken
        Optional<User> userExist = userRepo.findByEmail(user.getEmail());
        if(userExist.isPresent()) throw new EmailDuplicatedException(user.getEmail());
        //Save user
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
    public UserRoleDTO findAll() {
        List<User> entity = userRepo.findAll();
        List<UserDTO> userDTO = mapper.map(entity, new TypeToken<List<UserDTO>>(){}.getType());
        UserRoleDTO userRole = new UserRoleDTO(userDTO);
        return userRole;
    }
}
