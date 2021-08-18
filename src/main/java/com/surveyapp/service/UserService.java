package com.surveyapp.service;

import com.surveyapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
   User save(User user);
}
