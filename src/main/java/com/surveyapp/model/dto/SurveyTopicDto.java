package com.surveyapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyTopicDto {

    private int surveyId;
    private String surveyTopic;
}
