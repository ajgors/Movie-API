package com.ajgor.movieApi.service;

import com.ajgor.movieApi.entity.Movie;
import com.ajgor.movieApi.entity.Review;
import com.ajgor.movieApi.exception.MovieNotFoundException;
import com.ajgor.movieApi.exception.ReviewNotFoundException;
import com.ajgor.movieApi.repository.MovieRepository;
import com.ajgor.movieApi.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieService movieService;
    private final MovieRepository movieRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, MovieService movieService,
                         MovieRepository movieRepository){
        this.reviewRepository = reviewRepository;
        this.movieService = movieService;
        this.movieRepository = movieRepository;
    }

    public Review getMovieReview(Long movieId, Long reviewNumber) throws MovieNotFoundException{
        try {
            return movieService.getMovie(movieId).getReviews().get(reviewNumber.intValue());
        } catch (IndexOutOfBoundsException e) {
            throw new ReviewNotFoundException(reviewNumber);
        }
    }

    public List<Review> getReviewsByMovieId(Long movieId) throws MovieNotFoundException {
        return movieService.getMovie(movieId).getReviews();
    }

    public Review putReview(Long movieId, Review review) throws MovieNotFoundException{
        Movie movie = movieService.getMovie(movieId);
        movie.getReviews().add(review);
        movieRepository.save(movie);
        review.setMovie(movie);
        return reviewRepository.save(review);
    }
}
