package com.decathlon.alert.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setLocalDateTime(LocalDateTime.now());
        response.setErrorMsg(exception.getMessage());
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }
}
