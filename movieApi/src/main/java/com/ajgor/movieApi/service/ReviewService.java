package com.ajgor.movieApi.service;

import com.ajgor.movieApi.dto.ReviewRequest;
import com.ajgor.movieApi.dto.ReviewResponse;
import com.ajgor.movieApi.entity.Movie;
import com.ajgor.movieApi.entity.Review;
import com.ajgor.movieApi.exception.MovieNotFoundException;
import com.ajgor.movieApi.repository.MovieRepository;
import com.ajgor.movieApi.repository.ReviewRepository;
import com.ajgor.movieApi.specification.ReviewSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository,
                         MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    public Page<ReviewResponse> getReviewsByMovieId(Long movieId, Specification<Review> spec, Pageable pageable) {
        if (!movieRepository.existsById(movieId)) {
            throw new MovieNotFoundException(movieId);
        }

        Specification<Review> combinedSpec = Specification.where(spec).and(ReviewSpecification.movieId(movieId));

        return reviewRepository.findAll(combinedSpec, pageable).map(ReviewResponse::new);
    }

    public ReviewRequest putReview(Long movieId, ReviewRequest review) {
        try {
            Movie movie = movieRepository.getReferenceById(movieId);
            Review reviewToSave = Review.builder()
                    .movie(movie)
                    .author(review.getAuthor())
                    .rating(review.getRating())
                    .build();
            reviewToSave.setMovie(movie);
            reviewRepository.save(reviewToSave);
            return review;
        } catch (Exception e) {
            throw new MovieNotFoundException(movieId);
        }
    }
}
