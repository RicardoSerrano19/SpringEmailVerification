package com.serrano.app.helpdesk.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import com.serrano.app.helpdesk.domain.Role;
import com.serrano.app.helpdesk.domain.User;
import com.serrano.app.helpdesk.domain.dto.RoleToUserDTO;
import com.serrano.app.helpdesk.domain.dto.UserDTO;
import com.serrano.app.helpdesk.domain.dto.UserRoleDTO;
import com.serrano.app.helpdesk.enums.RoleName;
import com.serrano.app.helpdesk.exception.EmailDuplicatedException;
import com.serrano.app.helpdesk.exception.EmailNotFoundException;
import com.serrano.app.helpdesk.exception.RoleNotFoundException;
import com.serrano.app.helpdesk.repository.RoleRepository;
import com.serrano.app.helpdesk.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDTO save(UserDTO user) {
        //Find if email is taken
        Optional<User> userExist = userRepo.findByEmail(user.getEmail());
        if(userExist.isPresent()) throw new EmailDuplicatedException(user.getEmail());
        //Save user
        User entitySaved = mapper.map(user, User.class);
        entitySaved.setPassword(encoder.encode(user.getPassword()));
        entitySaved.setLocked(true);
        entitySaved.setEnabled(false);
        entitySaved = userRepo.save(entitySaved);
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

    @Transactional
    @Override
    public RoleToUserDTO addRole(String email, String roleName) {
        //Search user
        Optional<User> user = userRepo.findByEmail(email);
        if(!user.isPresent()) throw new EmailNotFoundException(email);
        //Search Role
        RoleName supported = RoleName.asRoleName(roleName);
        if(supported == null) throw new RoleNotFoundException(roleName);
        Optional<Role> roleExist = roleRepo.findByName(supported);
        if(!roleExist.isPresent()) throw new RoleNotFoundException(roleName);
        //Add role to user
        user.get().getRoles().add(roleExist.get());
        return new RoleToUserDTO(email, roleName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(username);
        if(!user.isPresent()) throw new EmailNotFoundException(username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.get().getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
        });
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), authorities);
    }
}
