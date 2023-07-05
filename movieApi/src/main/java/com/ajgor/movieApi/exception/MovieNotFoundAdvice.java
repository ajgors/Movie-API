package com.ajgor.movieApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class MovieNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String movieNotFoundHandler(MovieNotFoundException ex) {
        return ex.getMessage();
    }
}
