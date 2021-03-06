package com.surveyapp.service;

import com.surveyapp.exception.AlreadyExistsException;
import com.surveyapp.exception.NotFoundException;
import com.surveyapp.model.Survey;
import com.surveyapp.model.dto.SurveyDto;
import com.surveyapp.model.dto.SurveyTopicDto;
import com.surveyapp.repository.SurveyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository, ModelMapper modelMapper) {
        this.surveyRepository = surveyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SurveyTopicDto> getAllSurveys() {
        List<Survey> surveys =surveyRepository.findAll();
        return surveys.stream().map(survey -> modelMapper.map(survey,SurveyTopicDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<SurveyTopicDto> getBySurveyTopicContains(String surveyTopic) {
        List<Survey> surveys =surveyRepository.getBySurveyTopicContainsAndActivatedTrue(surveyTopic);
        return surveys.stream().map(survey -> modelMapper.map(survey,SurveyTopicDto.class)).collect(Collectors.toList());
    }

    @Override
    public SurveyDto getBySurveyId(int surveyId) {
        Survey survey;
        if (surveyRepository.findById(surveyId).isEmpty()){
            throw new NotFoundException("COULD NOT FOUND THE SURVEY");
        }
        survey=surveyRepository.getBySurveyId(surveyId);
        return modelMapper.map(survey,SurveyDto.class);
    }

    @Override
    public SurveyDto createSurvey(String surveyTopic, boolean activated) {
        if (surveyRepository.getBySurveyTopic(surveyTopic)!=null){
            throw new AlreadyExistsException("SURVEY TOPIC ALREADY EXISTS");
        }
        Survey newSurvey = new Survey();
        newSurvey.setSurveyTopic(surveyTopic);
        newSurvey.setActivated(activated);
        return modelMapper.map(surveyRepository.save(newSurvey),SurveyDto.class);
    }

    @Override
    public SurveyDto updateSurveyTopic(int surveyId, String surveyTopic) {
        Survey survey;
        if (surveyRepository.findById(surveyId).isEmpty()){
            throw new NotFoundException("COULD NOT FOUND THE SURVEY");
        }
        survey = surveyRepository.getBySurveyId(surveyId);
        survey.setSurveyTopic(surveyTopic);
        return modelMapper.map(surveyRepository.save(survey),SurveyDto.class);
    }

    @Override
    public void deleteSurvey(int surveyId) {
        if (surveyRepository.findById(surveyId).isEmpty()){
            throw new NotFoundException("COULD NOT FOUND THE SURVEY");
        }
        surveyRepository.deleteById(surveyId);
    }

    @Override
    public SurveyDto activateSurvey(int surveyId) {
        Survey survey;
        if (surveyRepository.findById(surveyId).isEmpty()){
            throw new NotFoundException("COULD NOT FOUND THE SURVEY");
        }
        survey = surveyRepository.getBySurveyId(surveyId);
        survey.setActivated(true);
        return modelMapper.map(surveyRepository.save(survey),SurveyDto.class);
    }

    @Override
    public List<SurveyTopicDto> getAllNonActiveSurveys() {
        List<Survey> surveys =surveyRepository.getAllNonActiveSurveys();
        return surveys.stream().map(survey -> modelMapper.map(survey,SurveyTopicDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<SurveyTopicDto> getAllActiveSurveys() {
        List<Survey> surveys =surveyRepository.getAllActiveSurveys();
        return surveys.stream().map(survey -> modelMapper.map(survey,SurveyTopicDto.class)).collect(Collectors.toList());
    }
}
