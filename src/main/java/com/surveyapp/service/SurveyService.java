package com.surveyapp.service;

import com.surveyapp.model.dto.SurveyDto;
import com.surveyapp.model.dto.SurveyTopicDto;

import java.util.List;

public interface SurveyService {
    List<SurveyTopicDto> getAllSurveys();
    List<SurveyTopicDto> getBySurveyTopicContains(String surveyTopic);
    SurveyDto getBySurveyId(int surveyId);
    SurveyDto createSurvey(String surveyTopic);
    SurveyDto updateSurveyTopic(int surveyId, String surveyTopic);
    void deleteSurvey(int surveyId);
    SurveyDto activateSurvey(int surveyId);
    List<SurveyTopicDto> getAllNonActiveSurveys();
    List<SurveyTopicDto> getAllActiveSurveys();
}
