

package com.surveyapp.config;

import com.surveyapp.model.Role;
import com.surveyapp.model.User;
import com.surveyapp.repository.UserRepository;
import com.surveyapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class DbInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    public DbInitializer(UserRepository userRepository, RoleService roleService, @Lazy BCryptPasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception{

        if(roleService.getByRoleName("ADMIN_USER")==null){
            roleService.addRole("ADMIN_USER");
            roleService.addRole("END_USER");
        }

        if (userRepository.getUserByUserMail("admin@gmail.com")==null){
            Role adminUser = roleService.getByRoleName("ADMIN_USER");
            Set<Role> roles = new HashSet<>();
            roles.add(adminUser);
            User newUser = new User();
            newUser.setUserName("admin");
            newUser.setUserSurname("admin");
            newUser.setUserMail("admin@gmail.com");
            newUser.setUserPassword(bcryptEncoder.encode("admin"));
            newUser.setUserRoles(roles);
            userRepository.save(newUser);
            System.out.println("Admin saved");
        }
    }
}
