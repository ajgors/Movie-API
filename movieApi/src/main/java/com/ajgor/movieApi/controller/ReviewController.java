package com.ajgor.movieApi.controller;

import com.ajgor.movieApi.entity.Review;
import com.ajgor.movieApi.exception.MovieNotFoundException;
import com.ajgor.movieApi.exception.ReviewNotFoundException;
import com.ajgor.movieApi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping("/{movieId}/reviews")
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long movieId) throws MovieNotFoundException{
        return ResponseEntity.ok(reviewService.getReviewsByMovieId(movieId));
    }

    @GetMapping("/{movieId}/reviews/{reviewNumber}")
    public ResponseEntity<Review> getReview(@PathVariable Long movieId, @PathVariable Long reviewNumber) throws MovieNotFoundException, ReviewNotFoundException {
        return ResponseEntity.ok(reviewService.getMovieReview(movieId, reviewNumber));
    }

    @PostMapping("/{movieId}/reviews")
    public ResponseEntity<Review> putReview(@PathVariable Long movieId, @RequestBody Review review) throws MovieNotFoundException {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reviewService.putReview(movieId, review).getId())
                .toUri();
        return ResponseEntity.created(uri).body(reviewService.putReview(movieId, review));
    }
}
