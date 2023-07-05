package com.ajgor.movieApi.exception;

public class ReviewNotFoundException extends RuntimeException{

    public ReviewNotFoundException(Long id) {
        super("review not found with id: " + id);
    }
}
