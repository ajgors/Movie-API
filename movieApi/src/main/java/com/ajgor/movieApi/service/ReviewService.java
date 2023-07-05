package com.ajgor.movieApi.service;

import com.ajgor.movieApi.dto.ReviewRequest;
import com.ajgor.movieApi.dto.ReviewResponse;
import com.ajgor.movieApi.entity.Movie;
import com.ajgor.movieApi.entity.Review;
import com.ajgor.movieApi.exception.MovieNotFoundException;
import com.ajgor.movieApi.exception.ReviewNotFoundException;
import com.ajgor.movieApi.repository.MovieRepository;
import com.ajgor.movieApi.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public ReviewResponse getMovieReview(Long movieId, Long reviewNumber) throws MovieNotFoundException{
        try {
            return new ReviewResponse(movieService.getMovie(movieId).getReviews().get(reviewNumber.intValue()));
        } catch (IndexOutOfBoundsException e) {
            throw new ReviewNotFoundException(reviewNumber);
        }
    }

    public List<ReviewResponse> getReviewsByMovieId(Long movieId) throws MovieNotFoundException {
        return movieService.getMovie(movieId).getReviews().stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());
    }

    public ReviewRequest putReview(Long movieId, ReviewRequest review){
        Optional<Movie> movie = movieRepository.findById(movieId);
        if(movie.isEmpty()){
            throw new MovieNotFoundException(movieId);
        }else{
            Review reviewToSave = Review.builder()
                            .movie(movie.get())
                            .author(review.getAuthor())
                            .rating(review.getRating())
                            .build();
            movie.get().getReviews().add(reviewToSave);
            movieRepository.save(movie.get());
            reviewToSave.setMovie(movie.get());
            reviewRepository.save(reviewToSave);
            return review;
        }
    }
}
