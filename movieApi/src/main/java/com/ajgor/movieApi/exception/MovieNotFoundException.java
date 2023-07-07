package com.ajgor.movieApi.exception;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(Long id) {
        super("movie not found with id: " + id);
    }
}
