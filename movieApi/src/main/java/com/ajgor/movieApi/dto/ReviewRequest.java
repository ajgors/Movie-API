package com.ajgor.movieApi.dto;

import com.ajgor.movieApi.entity.Review;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {

    @NotBlank(message = "author must be passed")
    private String author;

    @Column(columnDefinition = "integer CHECK (rating >= 0 AND rating <= 10)")
    @NotNull(message = "rating must be passed")
    private double rating;

    public ReviewRequest(Review review) {
        this.author = review.getAuthor();
        this.rating = review.getRating();
    }
}
