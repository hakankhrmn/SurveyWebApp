package com.surveyapp.controller;

import com.surveyapp.model.Survey;
import com.surveyapp.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SurveysController {

    private final SurveyService surveyService;

    @Autowired
    public SurveysController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @RequestMapping(value = "/get/{surveyId}", method = RequestMethod.GET)
    public ResponseEntity<Survey> getBySurveyId(@PathVariable("surveyId") int surveyId){
        Survey survey =surveyService.getBySurveyId(surveyId);
        return new ResponseEntity<>(survey, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllSurveys", method = RequestMethod.GET)
    public ResponseEntity<List<Survey>> getAllSurveys(){
        List<Survey> surveys = surveyService.getAllSurveys();
        return new ResponseEntity<>(surveys, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Survey> createSurvey(@RequestParam String surveyTopic){
        try {
            Survey survey = surveyService.createSurvey(surveyTopic);
            return new ResponseEntity<>(survey, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>((Survey) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/update/{surveyId}", method = RequestMethod.PUT)
    public ResponseEntity<Survey> updateSurveyTopic(@PathVariable("surveyId") int surveyId, @RequestParam String surveyTopic){
        try {
            Survey survey = surveyService.updateSurveyTopic(surveyId, surveyTopic);
            return new ResponseEntity<>(survey, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((Survey) null, HttpStatus.INTERNAL_SERVER_ERROR);
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
