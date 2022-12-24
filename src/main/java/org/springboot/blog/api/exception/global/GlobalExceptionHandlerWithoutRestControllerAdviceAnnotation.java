package org.springboot.blog.api.exception.global;


import org.springboot.blog.api.exception.UnHandledException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandlerWithoutRestControllerAdviceAnnotation {

    @ExceptionHandler(value = UnHandledException.class)
    @ResponseStatus(HttpStatus.OK)
    public String unhandledGlobalExceptionWithoutRestControllerAdviceAnnotation(UnHandledException exception) {
        return exception.getMessage();
    }
}
