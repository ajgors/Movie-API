package com.ajgor.movieApi.controller;

import com.ajgor.movieApi.dto.ReviewRequest;
import com.ajgor.movieApi.dto.ReviewResponse;
import com.ajgor.movieApi.entity.Review;
import com.ajgor.movieApi.exception.MovieNotFoundException;
import com.ajgor.movieApi.service.ReviewService;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class ReviewController {

    private final String DEFAULT_PAGE_NUMBER = "0";
    private final String DEFAULT_PAGE_SIZE = "10";
    private final String DEFAULT_SORTED_BY = "id";
    private final String DEFAULT_SORTED_DIR = "asc";
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{movieId}/reviews")
    public ResponseEntity<List<ReviewResponse>> getReviews(
            @PathVariable Long movieId,
//            @RequestParam(required = false) Double rating,
            @Spec(path = "rating", spec = GreaterThanOrEqual.class) Specification<Review> spec,
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(required = false, defaultValue = DEFAULT_SORTED_BY) String sortedBy,
            @RequestParam(required = false, defaultValue = DEFAULT_SORTED_DIR) String sortedDir
    ) throws MovieNotFoundException {
        return ResponseEntity.ok(reviewService.getReviewsByMovieId(spec, movieId, page, size, sortedBy, sortedDir));
    }

    @PostMapping("/{movieId}/reviews")
    public ResponseEntity<ReviewRequest> putReview(@PathVariable Long movieId, @RequestBody ReviewRequest review) throws MovieNotFoundException {
        return new ResponseEntity<>(reviewService.putReview(movieId, review), HttpStatus.CREATED);
    }
}
