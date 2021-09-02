package com.surveyapp.controller;

import com.surveyapp.model.dto.ResponseDto;
import com.surveyapp.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ResponsesController {

    private final ResponseService responseService;

    @Autowired
    public ResponsesController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping("/survey/{surveyId}/question/{questionId}/response/{responseId}")
    @PreAuthorize("hasAnyAuthority('ADMIN_USER','END_USER')")
    public ResponseEntity<ResponseDto> getByResponseId(@PathVariable("responseId") int responseId){
        ResponseDto responseDto = responseService.getByResponseId(responseId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/survey/{surveyId}/question/{questionId}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<ResponseDto> createResponse(@PathVariable("questionId") int questionId, @RequestParam String responseText){
        try {
            ResponseDto responseDto = responseService.createResponse(questionId, responseText);
            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((ResponseDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/survey/{surveyId}/question/{questionId}/response/{responseId}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<ResponseDto> updateResponse(@PathVariable("responseId") int responseId, @RequestParam String responseText){
        try {
            ResponseDto responseDto = responseService.updateResponse(responseId,responseText);
            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((ResponseDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/survey/{surveyId}/question/{questionId}/response/{responseId}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<HttpStatus> deleteResponse(@PathVariable("responseId") int responseId){
        try {
            responseService.deleteResponse(responseId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
