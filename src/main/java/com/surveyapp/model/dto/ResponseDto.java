package com.surveyapp.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    private int responseId;

    private String response;

    private List<UserDto> respondedUsers;

}
