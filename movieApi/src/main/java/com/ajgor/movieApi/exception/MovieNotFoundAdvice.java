package com.ajgor.movieApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@ControllerAdvice
public class MovieNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> movieNotFoundHandler(MovieNotFoundException ex)
    {
        Map<String, String> errorMap = new java.util.HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }
}
