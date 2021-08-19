package com.surveyapp.controller;

import com.surveyapp.model.Survey;
import com.surveyapp.model.dto.SurveyDto;
import com.surveyapp.service.SurveyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/survey")
public class SurveysController {

    private final SurveyService surveyService;
    private final ModelMapper modelMapper;


    @Autowired
    public SurveysController(SurveyService surveyService, ModelMapper modelMapper) {
        this.surveyService = surveyService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/get/{surveyId}", method = RequestMethod.GET)
    public ResponseEntity<SurveyDto> getBySurveyId(@PathVariable("surveyId") int surveyId){
        Survey survey =surveyService.getBySurveyId(surveyId);
        return new ResponseEntity<>(modelMapper.map(survey,SurveyDto.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllSurveys", method = RequestMethod.GET)
    public ResponseEntity<List<SurveyDto>> getAllSurveys(){
        List<Survey> surveys = surveyService.getAllSurveys();
        List<SurveyDto> surveyDtos = surveys.stream().map(survey -> modelMapper.map(survey,SurveyDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(surveyDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<SurveyDto> createSurvey(@RequestParam String surveyTopic){
        try {
            Survey survey = surveyService.createSurvey(surveyTopic);
            return new ResponseEntity<>(modelMapper.map(survey,SurveyDto.class), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>((SurveyDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/update/{surveyId}", method = RequestMethod.PUT)
    public ResponseEntity<SurveyDto> updateSurveyTopic(@PathVariable("surveyId") int surveyId, @RequestParam String surveyTopic){
        try {
            Survey survey = surveyService.updateSurveyTopic(surveyId, surveyTopic);
            return new ResponseEntity<>(modelMapper.map(survey,SurveyDto.class), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((SurveyDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/delete/{surveyId}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteSurvey(@PathVariable("surveyId") int surveyId){
        try {
            surveyService.deleteSurvey(surveyId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
