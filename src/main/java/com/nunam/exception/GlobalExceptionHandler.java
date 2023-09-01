package com.nunam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> exceptionHandler(Exception e, WebRequest w){
        ErrorDetails details = new ErrorDetails();
        details.setTimestamp(LocalDateTime.now());
        details.setMessage(e.getMessage());
        details.setDetails(w.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VehicleException.class)
    public ResponseEntity<ErrorDetails> VehicleExceptionHandler(VehicleException ex, WebRequest w){
        ErrorDetails details = new ErrorDetails();
        details.setMessage(ex.getMessage());
        details.setTimestamp(LocalDateTime.now());
        details.setDetails(w.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}
