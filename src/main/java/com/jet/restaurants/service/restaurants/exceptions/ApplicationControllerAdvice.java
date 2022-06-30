package com.jet.restaurants.service.restaurants.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class ApplicationControllerAdvice {

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<ErrorResponse> handleException(Throwable ex) {
        if (log.isErrorEnabled()) {
            log.error("Unknown exception: " + ex.getMessage(), ex);
        }
        ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage());
        return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> return404(NoSuchElementException ex) {
        if (log.isErrorEnabled()) {
            log.error("Not found Exception: " + ex.getMessage(), ex);
        }
        ErrorResponse errorResponse = new ErrorResponse("404", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

}
