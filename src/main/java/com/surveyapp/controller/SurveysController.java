package com.surveyapp.controller;

import com.surveyapp.model.dto.SurveyDto;
import com.surveyapp.model.dto.SurveyTopicDto;
import com.surveyapp.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @PreAuthorize("hasAnyAuthority('ADMIN_USER','END_USER')")
    public ResponseEntity<SurveyDto> getBySurveyId(@PathVariable("surveyId") int surveyId){
        SurveyDto surveyDto =surveyService.getBySurveyId(surveyId);
        return new ResponseEntity<>(surveyDto, HttpStatus.OK);
    }
    @GetMapping("/{surveyId}/results")
    @PreAuthorize("hasAnyAuthority('ADMIN_USER','END_USER')")
    public ResponseEntity<SurveyDto> getResults(@PathVariable("surveyId") int surveyId){
        SurveyDto surveyDto =surveyService.getBySurveyId(surveyId);
        return new ResponseEntity<>(surveyDto, HttpStatus.OK);
    }

    @GetMapping("/nonactives")
    @PreAuthorize("hasAnyAuthority('ADMIN_USER')")
    public ResponseEntity<List<SurveyTopicDto>> getAllNonActiveSurveys(){
        List<SurveyTopicDto> surveyDtos = surveyService.getAllNonActiveSurveys();
        return new ResponseEntity<>(surveyDtos, HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN_USER','END_USER')")
    public ResponseEntity<List<SurveyTopicDto>> getAllSurveys(){
        List<SurveyTopicDto> surveyDtos = surveyService.getAllActiveSurveys();
        return new ResponseEntity<>(surveyDtos, HttpStatus.OK);
    }

    @GetMapping("/browse")
    @PreAuthorize("hasAnyAuthority('ADMIN_USER','END_USER')")
    public ResponseEntity<List<SurveyTopicDto>> getBySurveyTopicContains(@RequestParam String surveyTopic){
        List<SurveyTopicDto> surveyDtos = surveyService.getBySurveyTopicContains(surveyTopic);
        return new ResponseEntity<>(surveyDtos, HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN_USER','END_USER')")
    public ResponseEntity<SurveyDto> createSurvey(@RequestParam String surveyTopic){
        try {
            SurveyDto surveyDto;
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN_USER"))) {
                surveyDto = surveyService.createSurvey(surveyTopic, true);
                return new ResponseEntity<>(surveyDto, HttpStatus.CREATED);
            }

            surveyDto = surveyService.createSurvey(surveyTopic, false);
            return new ResponseEntity<>(surveyDto, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>((SurveyDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/nonactives/{surveyId}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<SurveyDto> activateSurvey(@PathVariable("surveyId") int surveyId){
        try {
            SurveyDto surveyDto = surveyService.activateSurvey(surveyId);
            return new ResponseEntity<>(surveyDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((SurveyDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{surveyId}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<SurveyDto> updateSurveyTopic(@PathVariable("surveyId") int surveyId, @RequestParam String surveyTopic){
        try {
            SurveyDto surveyDto = surveyService.updateSurveyTopic(surveyId, surveyTopic);
            return new ResponseEntity<>(surveyDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((SurveyDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{surveyId}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<HttpStatus> deleteSurvey(@PathVariable("surveyId") int surveyId){
        try {
            surveyService.deleteSurvey(surveyId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
