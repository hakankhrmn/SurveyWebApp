package com.surveyapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyDto {

    private int surveyId;

    private String surveyTopic;

    private List<QuestionDto> questions;
}