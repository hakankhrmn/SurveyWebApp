package com.surveyapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {
    private String message;
    public AlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
    public AlreadyExistsException() {
    }
}
