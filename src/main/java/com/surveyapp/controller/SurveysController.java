package com.surveyapp.controller;

import com.surveyapp.model.dto.SurveyDto;
import com.surveyapp.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveysController {

    private final SurveyService surveyService;

    @Autowired
    public SurveysController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @RequestMapping(value = "/get/{surveyId}", method = RequestMethod.GET)
    public ResponseEntity<SurveyDto> getBySurveyId(@PathVariable("surveyId") int surveyId){
        SurveyDto surveyDto =surveyService.getBySurveyId(surveyId);
        return new ResponseEntity<>(surveyDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllSurveys", method = RequestMethod.GET)
    public ResponseEntity<List<SurveyDto>> getAllSurveys(){
        List<SurveyDto> surveyDtos = surveyService.getAllSurveys();
        return new ResponseEntity<>(surveyDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<SurveyDto> createSurvey(@RequestParam String surveyTopic){
        try {
            SurveyDto surveyDto = surveyService.createSurvey(surveyTopic);
            return new ResponseEntity<>(surveyDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>((SurveyDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/update/{surveyId}", method = RequestMethod.PUT)
    public ResponseEntity<SurveyDto> updateSurveyTopic(@PathVariable("surveyId") int surveyId, @RequestParam String surveyTopic){
        try {
            SurveyDto surveyDto = surveyService.updateSurveyTopic(surveyId, surveyTopic);
            return new ResponseEntity<>(surveyDto, HttpStatus.OK);
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
