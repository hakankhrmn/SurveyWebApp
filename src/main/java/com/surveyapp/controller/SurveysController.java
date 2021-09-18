package com.surveyapp.controller;

import com.surveyapp.model.Response;
import com.surveyapp.model.User;
import com.surveyapp.model.dto.ResponseIdsDto;
import com.surveyapp.model.dto.SurveyDto;
import com.surveyapp.model.dto.SurveyTopicDto;
import com.surveyapp.service.ResponseService;
import com.surveyapp.service.SurveyService;
import com.surveyapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class SurveysController {

    private final SurveyService surveyService;
    private final UserService userService;
    private final ResponseService responseService;

    @Autowired
    public SurveysController(SurveyService surveyService, UserService userService, ResponseService responseService) {
        this.surveyService = surveyService;
        this.userService = userService;
        this.responseService = responseService;
    }

    @GetMapping("/survey/{surveyId}")
    @PreAuthorize("hasAnyAuthority('ADMIN_USER','END_USER')")
    public ResponseEntity<SurveyDto> getBySurveyId(@PathVariable("surveyId") int surveyId){
        SurveyDto surveyDto =surveyService.getBySurveyId(surveyId);
        return new ResponseEntity<>(surveyDto, HttpStatus.OK);
    }
    @GetMapping("/survey/{surveyId}/results")
    @PreAuthorize("hasAnyAuthority('ADMIN_USER','END_USER')")
    public ResponseEntity<SurveyDto> getResults(@PathVariable("surveyId") int surveyId){
        SurveyDto surveyDto =surveyService.getBySurveyId(surveyId);
        return new ResponseEntity<>(surveyDto, HttpStatus.OK);
    }

    @GetMapping("/nonactives")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<List<SurveyTopicDto>> getAllNonActiveSurveys(){
        List<SurveyTopicDto> surveyDtos = surveyService.getAllNonActiveSurveys();
        return new ResponseEntity<>(surveyDtos, HttpStatus.OK);
    }

    @GetMapping("/survey")
    @PreAuthorize("hasAnyAuthority('ADMIN_USER','END_USER')")
    public ResponseEntity<List<SurveyTopicDto>> getAllSurveys(){
        List<SurveyTopicDto> surveyDtos = surveyService.getAllActiveSurveys();
        return new ResponseEntity<>(surveyDtos, HttpStatus.OK);
    }

    @GetMapping("/browse")
    @PreAuthorize("hasAnyAuthority('ADMIN_USER','END_USER')")
    public ResponseEntity<List<SurveyTopicDto>> getBySurveyTopicContains(@RequestBody SurveyDto surveyDto){
        List<SurveyTopicDto> surveyDtos = surveyService.getBySurveyTopicContains(surveyDto.getSurveyTopic());
        return new ResponseEntity<>(surveyDtos, HttpStatus.OK);
    }

    @PostMapping("/survey")
    @PreAuthorize("hasAnyAuthority('ADMIN_USER','END_USER')")
    public ResponseEntity<SurveyDto> createSurvey(@RequestBody SurveyDto surveyDto){
        try {
            SurveyDto newsurveyDto;
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN_USER"))) {
                newsurveyDto = surveyService.createSurvey(surveyDto.getSurveyTopic(), true);
                return new ResponseEntity<>(newsurveyDto, HttpStatus.CREATED);
            }

            newsurveyDto = surveyService.createSurvey(surveyDto.getSurveyTopic(), false);
            return new ResponseEntity<>(newsurveyDto, HttpStatus.CREATED);

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

    @PutMapping("/survey/{surveyId}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<SurveyDto> updateSurveyTopic(@PathVariable("surveyId") int surveyId, @RequestBody SurveyDto surveyDto){
        try {
            SurveyDto updateSurveyTopic= surveyService.updateSurveyTopic(surveyId, surveyDto.getSurveyTopic());
            return new ResponseEntity<>(updateSurveyTopic, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((SurveyDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/survey/{surveyId}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<HttpStatus> deleteSurvey(@PathVariable("surveyId") int surveyId){
        try {
            surveyService.deleteSurvey(surveyId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/survey/{surveyId}/results")
    @PreAuthorize("hasAnyAuthority('ADMIN_USER','END_USER')")
    public ResponseEntity<SurveyDto> submitSurvey(@PathVariable("surveyId") int surveyId, @RequestBody ResponseIdsDto responseIdsDto){
        try {
            String username;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            }else {
                username = principal.toString();
            }

            User user = userService.getUserByUserMail(username);
            List<Response> responses = new ArrayList<>();
            for (int responseId : responseIdsDto.getResponseIds()) {
                responses.add(responseService.findResponse(responseId));
            }
            userService.addResponses(user,responses);
            SurveyDto surveyDto =surveyService.getBySurveyId(surveyId);
            return new ResponseEntity<>(surveyDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>((SurveyDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
