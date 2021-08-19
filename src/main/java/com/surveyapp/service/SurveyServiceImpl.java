package com.surveyapp.service;

import com.surveyapp.exception.AlreadyExistsException;
import com.surveyapp.exception.NotFoundException;
import com.surveyapp.model.Survey;
import com.surveyapp.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey getBySurveyId(int surveyId) {
        Survey survey;
        if (surveyRepository.findById(surveyId).isEmpty()){
            throw new NotFoundException("COULD NOT FOUND THE SURVEY");
        }
        survey=surveyRepository.getBySurveyId(surveyId);
        return survey;
    }

    @Override
    public Survey createSurvey(String surveyTopic) {
        if (surveyRepository.getBySurveyTopic(surveyTopic)!=null){
            throw new AlreadyExistsException("SURVEY TOPIC ALREADY EXISTS");
        }
        Survey newSurvey = new Survey();
        newSurvey.setSurveyTopic(surveyTopic);
        return surveyRepository.save(newSurvey);
    }

    @Override
    public Survey updateSurveyTopic(int surveyId, String surveyTopic) {
        Survey survey;
        if (surveyRepository.findById(surveyId).isEmpty()){
            throw new NotFoundException("COULD NOT FOUND THE SURVEY");
        }
        survey = getBySurveyId(surveyId);
        survey.setSurveyTopic(surveyTopic);
        return surveyRepository.save(survey);
    }

    @Override
    public void deleteSurvey(int surveyId) {
        if (surveyRepository.findById(surveyId).isEmpty()){
            throw new NotFoundException("COULD NOT FOUND THE SURVEY");
        }
        surveyRepository.deleteById(surveyId);
    }
}
