package com.surveyapp.service;

import com.surveyapp.model.dto.QuestionDto;

public interface QuestionService {
    QuestionDto getByQuestionId(int questionId);
    QuestionDto createQuestion(int surveyId, String questionText);
    QuestionDto updateQuestion(int questionId, String questionText);
    void deleteQuestion(int questionId);
}
