package com.surveyapp.service;

import com.surveyapp.model.User;
import com.surveyapp.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
   User save(UserDto userDto);
}
