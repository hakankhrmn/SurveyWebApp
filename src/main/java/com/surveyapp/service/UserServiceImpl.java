package com.surveyapp.service;

import com.surveyapp.dao.UserDao;
import com.surveyapp.model.MyUserDetails;
import com.surveyapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String userMail) throws UsernameNotFoundException {
        User user = userDao.getUserByUserMail(userMail);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }
}
