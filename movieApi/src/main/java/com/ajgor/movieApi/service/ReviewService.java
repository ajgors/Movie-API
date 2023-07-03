package com.ajgor.movieApi.service;

import com.ajgor.movieApi.entity.Movie;
import com.ajgor.movieApi.entity.Review;
import com.ajgor.movieApi.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieService movieService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, MovieService movieService){
        this.reviewRepository = reviewRepository;
        this.movieService = movieService;
    }

    public Review getMovieReview(Long movieId, Long reviewNumber) throws EntityNotFoundException{
        try{
            return movieService.getMovie(movieId).getReviews().get(reviewNumber.intValue());
        }catch (IndexOutOfBoundsException e){
            throw new EntityNotFoundException("Review not found");
        }
    }

    public List<Review> getReviewsByMoveId(Long movieId) throws EntityNotFoundException{
        return movieService.getMovie(movieId).getReviews();
    }

    public Review putReview(Long movieId, Review review) throws EntityNotFoundException{
        Movie movie = movieService.getMovie(movieId);
        movie.getReviews().add(review);
        return reviewRepository.save(review);
    }
}
