package com.surveyapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private String message;
    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public NotFoundException() {
    }
}
