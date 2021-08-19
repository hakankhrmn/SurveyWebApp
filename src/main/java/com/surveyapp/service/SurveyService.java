package com.surveyapp.service;

import com.surveyapp.model.Survey;

import java.util.List;

public interface SurveyService {
    List<Survey> getAllSurveys();
    Survey getBySurveyId(int surveyId);
    Survey createSurvey(String surveyTopic);
    Survey updateSurveyTopic(int surveyId, String surveyTopic);
    void deleteSurvey(int surveyId);
}
