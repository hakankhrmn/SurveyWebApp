package com.surveyapp.controller;

import com.surveyapp.exception.AlreadyExistsException;
import com.surveyapp.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@CrossOrigin
public class GlobalExceptionHandler {
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> alreadyExistsException(){
        return new ResponseEntity<>("AlreadyExistsException", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundException(){
        return new ResponseEntity<>("NotFoundException", HttpStatus.NOT_FOUND);
    }
}
