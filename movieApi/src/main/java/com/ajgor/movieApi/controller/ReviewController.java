package com.ajgor.movieApi.controller;

import com.ajgor.movieApi.entity.Review;
import com.ajgor.movieApi.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long movieId){
        try{
            return ResponseEntity.ok(reviewService.getReviewsByMoveId(movieId));
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{movieId}/reviews/{reviewNumber}")
    public ResponseEntity<Review> getReview(@PathVariable Long movieId, @PathVariable Long reviewNumber){
        try {
            Review review = reviewService.getMovieReview(movieId, reviewNumber);
            return ResponseEntity.ok(review);
        }catch (EntityNotFoundException | IndexOutOfBoundsException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{movieId}/reviews")
    public ResponseEntity<Review> putReview(@PathVariable Long movieId, @RequestBody Review review){
        try{
            return ResponseEntity.ok(reviewService.putReview(movieId, review));
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
