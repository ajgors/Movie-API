package com.ajgor.movieApi.controller;

import com.ajgor.movieApi.dto.ReviewRequest;
import com.ajgor.movieApi.dto.ReviewResponse;
import com.ajgor.movieApi.entity.Review;
import com.ajgor.movieApi.exception.MovieNotFoundException;
import com.ajgor.movieApi.service.ReviewService;
import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{movieId}/reviews")
    public ResponseEntity<Page<ReviewResponse>> getReviews(
            @PathVariable Long movieId,
            @Filter Specification<Review> spec,
            Pageable pageable
    ) throws MovieNotFoundException {
        return ResponseEntity.ok(reviewService.getReviewsByMovieId(movieId, spec, pageable));
    }

    @PostMapping("/{movieId}/reviews")
    public ResponseEntity<ReviewRequest> putReview(@PathVariable Long movieId, @RequestBody @Valid ReviewRequest review) throws MovieNotFoundException {
        return new ResponseEntity<>(reviewService.putReview(movieId, review), HttpStatus.CREATED);
    }
}
