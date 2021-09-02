package com.surveyapp.model;

import com.surveyapp.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private UserDto userDto;
}
