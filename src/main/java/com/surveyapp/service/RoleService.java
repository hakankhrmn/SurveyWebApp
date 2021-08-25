package com.surveyapp.service;

import com.surveyapp.model.Role;

public interface RoleService {
    Role getByRoleName(String roleName);
    void addRole(String roleName);
}
