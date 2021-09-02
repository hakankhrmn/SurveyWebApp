package com.surveyapp.controller;

import com.surveyapp.model.dto.QuestionDto;
import com.surveyapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class QuestionsController {

    private final QuestionService questionService;

    @Autowired
    public QuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/survey/{surveyId}/question/{questionId}")
    @PreAuthorize("hasAnyAuthority('ADMIN_USER','END_USER')")
    public ResponseEntity<QuestionDto> getByQuestionId(@PathVariable("questionId") int questionId){
        QuestionDto questionDto = questionService.getByQuestionId(questionId);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    @PostMapping("/survey/{surveyId}/question")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<QuestionDto> createQuestion(@PathVariable("surveyId") int surveyId, @RequestParam String questionText){
        try {
            QuestionDto questionDto = questionService.createQuestion(surveyId, questionText);
            return new ResponseEntity<>(questionDto,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((QuestionDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/survey/{surveyId}/question/{questionId}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<QuestionDto> updateQuestion(@PathVariable("questionId") int questionId, @RequestParam String questionText){
        try {
            QuestionDto questionDto = questionService.updateQuestion(questionId,questionText);
            return new ResponseEntity<>(questionDto,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((QuestionDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/survey/{surveyId}/question/{questionId}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable("questionId") int questionId){
        try {
            questionService.deleteQuestion(questionId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
