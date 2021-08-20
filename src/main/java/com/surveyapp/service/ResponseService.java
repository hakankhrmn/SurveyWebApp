package com.surveyapp.service;

import com.surveyapp.model.dto.ResponseDto;

public interface ResponseService {
    ResponseDto getByResponseId(int responseId);
    ResponseDto createResponse(int questionId, String responseText);
    ResponseDto updateResponse(int responseId, String responseText);
    void deleteResponse(int responseId);
}
