package com.surveyapp.service;

import com.surveyapp.model.User;
import com.surveyapp.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
   UserDto save(UserDto userDto);
   void updateResetPasswordToken(String token, String email);
   User getByResetPasswordToken(String token);
   void updatePassword(User user, String newPassword);
}
