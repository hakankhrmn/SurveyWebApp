package com.surveyapp.service;

import com.surveyapp.exception.NotFoundException;
import com.surveyapp.model.Role;
import com.surveyapp.model.User;
import com.surveyapp.model.dto.UserDto;
import com.surveyapp.repository.UserRepository;
import com.surveyapp.model.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;
    private final RoleService roleService;
    private final ModelMapper modelMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder bcryptEncoder, RoleService roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String userMail) throws UsernameNotFoundException {

        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        User user = userRepository.getUserByUserMail(userMail);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        for(Role role : user.getUserRoles()){
            roles.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserMail(), user.getUserPassword(), roles);
    }

    @Override
    public UserDto save(UserDto userDto) {

        Role adminUser = roleService.getByRoleName("ADMIN_USER");
        Role endUser = roleService.getByRoleName("END_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(endUser);
        User newUser = new User();
        newUser.setUserName(userDto.getUserName());
        newUser.setUserSurname(userDto.getUserSurname());
        newUser.setUserMail(userDto.getUserMail());
        newUser.setUserPassword(bcryptEncoder.encode(userDto.getUserPassword()));
        newUser.setUserRoles(roles);
        return modelMapper.map(userRepository.save(newUser),UserDto.class);
    }

    @Override
    public void updateResetPasswordToken(String token, String email) {
        User user = userRepository.getUserByUserMail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new NotFoundException("Could not find any customer with the email " + email);
        }
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.getByResetPasswordToken(token);
    }

    @Override
    public User getUserByUserMail(String username) {
        return userRepository.getUserByUserMail(username);
    }

    @Override
    public void updatePassword(User user, String newPassword) {

        String encodedPassword = bcryptEncoder.encode(newPassword);
        user.setUserPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

    @Override
    public void addResponses(User user, List<Response> responses) {
        List<Response> oldResponses = user.getUserResponses();
        for (Response response: responses){
            oldResponses.add(response);
        }
        user.setUserResponses(oldResponses);
        userRepository.save(user);
    }
}


