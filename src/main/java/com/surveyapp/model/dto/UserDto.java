package com.surveyapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userName;
    private String userSurname;
    private String userMail;
    private String userPassword;

}
