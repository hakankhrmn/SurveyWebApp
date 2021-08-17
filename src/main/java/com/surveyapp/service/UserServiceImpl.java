package com.surveyapp.service;

import com.surveyapp.model.MyUserDetails;
import com.surveyapp.model.User;
import com.surveyapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        User user = userRepository.getUserByUserMail(userMail);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }
}
