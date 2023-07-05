package com.ajgor.movieApi.service;

import com.ajgor.movieApi.dto.MovieRequest;
import com.ajgor.movieApi.dto.MovieResponse;
import com.ajgor.movieApi.exception.MovieNotFoundException;
import com.ajgor.movieApi.entity.Movie;
import com.ajgor.movieApi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public List<MovieResponse> getMovies(){
        return movieRepository.findAll().stream()
                .map(MovieResponse::new)
                .collect(Collectors.toList());
    }

    public MovieResponse getMovie(Long id){
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        return new MovieResponse(movie);
    }

    public MovieRequest putMovie(MovieRequest movie){
        Movie movieToSave = Movie.builder()
                .title(movie.getTitle())
                .description(movie.getDescription())
                .date(movie.getDate())
                .genres(movie.getGenres())
                .poster(movie.getPoster())
                .reviews(movie.getReviews())
                .build();

        movieRepository.save(movieToSave);
        return movie;
    }
}
