package com.surveyapp.service;

import com.surveyapp.model.dto.SurveyDto;

import java.util.List;

public interface SurveyService {
    List<SurveyDto> getAllSurveys();
    SurveyDto getBySurveyId(int surveyId);
    SurveyDto createSurvey(String surveyTopic);
    SurveyDto updateSurveyTopic(int surveyId, String surveyTopic);
    void deleteSurvey(int surveyId);
}
