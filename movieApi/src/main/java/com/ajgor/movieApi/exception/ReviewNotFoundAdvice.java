package com.ajgor.movieApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class ReviewNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ReviewNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> reviewNotFoundHandler(ReviewNotFoundException ex) {
        Map<String, String> errorMap = new java.util.HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }
}
