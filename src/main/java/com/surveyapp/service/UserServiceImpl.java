package com.surveyapp.service;

import com.surveyapp.model.Role;
import com.surveyapp.model.User;
import com.surveyapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder bcryptEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
    }


/*
    private Map<String,String> users = new HashMap<>();
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @PostConstruct
    public void init(){
        users.put("xxx",passwordEncoder.encode("123"));
    }

*/
    @Override
    public UserDetails loadUserByUsername(String userMail) throws UsernameNotFoundException {

/*

        if (users.containsKey(userMail)){
            return new User(userMail,users.get(userMail),new ArrayList<>());
        }
        throw new UsernameNotFoundException(userMail);
*/

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
    public User save(User user) {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setUserSurname(user.getUserSurname());
        newUser.setUserMail(user.getUserMail());
        newUser.setUserPassword(bcryptEncoder.encode(user.getUserPassword()));
        newUser.setUserRoles(user.getUserRoles());
        return userRepository.save(newUser);
    }
}
