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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieService movieService;
    private final MovieRepository movieRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, MovieService movieService,
                         MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieService = movieService;
        this.movieRepository = movieRepository;
    }

    public List<ReviewResponse> getReviewsByMovieId(Specification<Review> spec, Long movieId, Integer page, Integer size, String sortedBy, String sortedDir) {
        movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));

        Sort sort = sortedDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortedBy).ascending() : Sort.by(sortedBy).descending();

        Specification<Review> combinedSpec = Specification.where(spec).and(ReviewSpecification.movieId(movieId));

        Pageable pageable = PageRequest.of(page, size, sort);

        return reviewRepository.findAll(combinedSpec, pageable)
                .stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());
    }

    public ReviewRequest putReview(Long movieId, ReviewRequest review) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        Review reviewToSave = Review.builder()
                .movie(movie)
                .author(review.getAuthor())
                .rating(review.getRating())
                .build();
        reviewToSave.setMovie(movie);
        reviewRepository.save(reviewToSave);
        return review;
    }
}
