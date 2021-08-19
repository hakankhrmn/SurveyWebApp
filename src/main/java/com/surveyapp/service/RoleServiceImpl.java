package com.surveyapp.service;

import com.surveyapp.model.Role;
import com.surveyapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getByRoleName(String roleName) {
        return roleRepository.getByRoleName(roleName);
    }
}
