package com.ajgor.movieApi.dto;

import com.ajgor.movieApi.entity.Review;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponse {

    private String author;
    private double rating;

    public ReviewResponse(Review review){
        this.author = review.getAuthor();
        this.rating = review.getRating();
    }
}
