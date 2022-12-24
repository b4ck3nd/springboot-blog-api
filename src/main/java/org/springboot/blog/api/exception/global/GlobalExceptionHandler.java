package org.springboot.blog.api.exception.global;


import org.springboot.blog.api.exception.UnHandledException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value =UnHandledException.class)
    public ResponseEntity<String> unhandledGlobalException(UnHandledException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.OK);
    }


}
