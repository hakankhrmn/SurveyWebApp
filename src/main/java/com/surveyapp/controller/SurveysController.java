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

    @GetMapping("/{surveyId}")
    public ResponseEntity<SurveyDto> getBySurveyId(@PathVariable("surveyId") int surveyId){
        SurveyDto surveyDto =surveyService.getBySurveyId(surveyId);
        return new ResponseEntity<>(surveyDto, HttpStatus.OK);
    }
    @GetMapping("/{surveyId}/results")
    public ResponseEntity<SurveyDto> getResults(@PathVariable("surveyId") int surveyId){
        SurveyDto surveyDto =surveyService.getBySurveyId(surveyId);
        return new ResponseEntity<>(surveyDto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<SurveyDto>> getAllSurveys(){
        List<SurveyDto> surveyDtos = surveyService.getAllSurveys();
        return new ResponseEntity<>(surveyDtos, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<SurveyDto> createSurvey(@RequestParam String surveyTopic){
        try {
            SurveyDto surveyDto = surveyService.createSurvey(surveyTopic);
            return new ResponseEntity<>(surveyDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>((SurveyDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{surveyId}")
    public ResponseEntity<SurveyDto> updateSurveyTopic(@PathVariable("surveyId") int surveyId, @RequestParam String surveyTopic){
        try {
            SurveyDto surveyDto = surveyService.updateSurveyTopic(surveyId, surveyTopic);
            return new ResponseEntity<>(surveyDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((SurveyDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{surveyId}")
    public ResponseEntity<HttpStatus> deleteSurvey(@PathVariable("surveyId") int surveyId){
        try {
            surveyService.deleteSurvey(surveyId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
