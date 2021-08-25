package com.surveyapp.service;

import com.surveyapp.model.User;
import com.surveyapp.model.dto.UserDto;
import com.surveyapp.model.Response;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
   UserDto save(UserDto userDto);
   void updateResetPasswordToken(String token, String email);
   User getByResetPasswordToken(String token);
   User getUserByUserMail(String username);
   void updatePassword(User user, String newPassword);
   void addResponses(User user, List<Response> responses);
}
