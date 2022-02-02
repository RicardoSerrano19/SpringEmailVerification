package com.serrano.app.helpdesk.service;

import java.util.List;
import java.util.Optional;

import com.serrano.app.helpdesk.domain.Role;
import com.serrano.app.helpdesk.domain.dto.RoleDTO;
import com.serrano.app.helpdesk.domain.dto.RolesDTO;
import com.serrano.app.helpdesk.enums.RoleName;
import com.serrano.app.helpdesk.exception.RoleNotFoundException;
import com.serrano.app.helpdesk.repository.RoleRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleRepository roleRepo;
    @Autowired
    ModelMapper mapper;

    @Override
    public RoleDTO get(String name) {
        RoleName supported = RoleName.asRoleName(name);
        if(supported == null) throw new RoleNotFoundException(name);
        Optional<RoleName> roleExist = roleRepo.findByName(supported);
        if(!roleExist.isPresent()) throw new RoleNotFoundException(name);
        RoleDTO roleDTO = mapper.map(roleExist.get(), RoleDTO.class);
        return roleDTO;
    }

    @Override
    public RolesDTO getAll() {
        List<Role> entity = roleRepo.findAll();
        List<RoleDTO> usersDTO = mapper.map(entity, new TypeToken<List<RoleDTO>>(){}.getType());
        RolesDTO roles = new RolesDTO(usersDTO);
        return roles;
    }
}
