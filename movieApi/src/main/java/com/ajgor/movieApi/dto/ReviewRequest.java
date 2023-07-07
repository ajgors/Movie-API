package com.ajgor.movieApi.dto;

import com.ajgor.movieApi.entity.Review;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ReviewRequest {

    @NotBlank(message = "author must be passed")
    private String author;

    @Min(value = 1, message = "rating can't be less than 1")
    @Max(value = 10, message = "rating can't be greater than 10")
    @NotNull(message = "rating must be passed")
    private Double rating;

    public ReviewRequest(Review review) {
        this.author = review.getAuthor();
        this.rating = review.getRating();
    }
}
