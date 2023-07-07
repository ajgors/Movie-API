package com.ajgor.movieApi.controller;

import com.ajgor.movieApi.dto.ReviewRequest;
import com.ajgor.movieApi.dto.ReviewResponse;
import com.ajgor.movieApi.exception.MovieNotFoundException;
import com.ajgor.movieApi.exception.ReviewNotFoundException;
import com.ajgor.movieApi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{movieId}/reviews")
    public ResponseEntity<List<ReviewResponse>> getReviews(@PathVariable Long movieId) throws MovieNotFoundException {
        return ResponseEntity.ok(reviewService.getReviewsByMovieId(movieId));
    }

    @GetMapping("/{movieId}/reviews/{reviewNumber}")
    public ResponseEntity<ReviewResponse> getReview(@PathVariable Long movieId, @PathVariable Long reviewNumber) throws MovieNotFoundException, ReviewNotFoundException {
        return ResponseEntity.ok(reviewService.getMovieReview(movieId, reviewNumber));
    }

    @PostMapping("/{movieId}/reviews")
    public ResponseEntity<ReviewRequest> putReview(@PathVariable Long movieId, @RequestBody ReviewRequest review) throws MovieNotFoundException {
        return new ResponseEntity<>(reviewService.putReview(movieId, review), HttpStatus.CREATED);
    }
}
