package com.gutinicolas.twitter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ExcpetionsController {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Map<String, Object>> handleThrowable(final Throwable ex) {
        return new ResponseEntity<Map<String, Object>>(Map.of("status", "error", "message", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}