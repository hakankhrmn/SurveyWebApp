package com.surveyapp.service;

import com.surveyapp.exception.AlreadyExistsException;
import com.surveyapp.exception.NotFoundException;
import com.surveyapp.model.Response;
import com.surveyapp.model.dto.ResponseDto;
import com.surveyapp.repository.QuestionRepository;
import com.surveyapp.repository.ResponseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceImpl implements ResponseService{

    private final ResponseRepository responseRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ResponseServiceImpl(ResponseRepository responseRepository, QuestionRepository questionRepository, ModelMapper modelMapper) {
        this.responseRepository = responseRepository;
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseDto getByResponseId(int responseId) {
        Response response;
        if (responseRepository.findById(responseId).isEmpty()){
            throw new NotFoundException("COULD NOT FOUND THE RESPONSE");
        }
        response = responseRepository.findById(responseId).get();
        return modelMapper.map(response,ResponseDto.class);
    }

    @Override
    public ResponseDto createResponse(int questionId, String responseText) {
        Response response = responseRepository.getByResponse(responseText);
        if (response !=null && response.getQuestion().getQuestionId()==questionId){
            throw new AlreadyExistsException("RESPONSE ALREADY EXISTS");
        }
        Response newResponse = new Response();
        newResponse.setResponseText(responseText);
        newResponse.setQuestion(questionRepository.findById(questionId).get());
        return modelMapper.map(newResponse,ResponseDto.class);
    }

    @Override
    public ResponseDto updateResponse(int responseId, String responseText) {
        Response response;
        if (responseRepository.findById(responseId).isEmpty()){
            throw new NotFoundException("COULD NOT FOUND THE RESPONSE");
        }
        response = responseRepository.findById(responseId).get();
        response.setResponseText(responseText);
        return modelMapper.map(responseRepository.save(response),ResponseDto.class);
    }

    @Override
    public void deleteResponse(int responseId) {
        if (responseRepository.findById(responseId).isEmpty()){
            throw new NotFoundException("COULD NOT FOUND THE RESPONSE");
        }
        responseRepository.deleteById(responseId);
    }
}
