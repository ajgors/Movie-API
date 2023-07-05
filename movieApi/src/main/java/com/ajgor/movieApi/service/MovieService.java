package com.ajgor.movieApi.service;

import com.ajgor.movieApi.exception.MovieNotFoundException;
import com.ajgor.movieApi.entity.Movie;
import com.ajgor.movieApi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }

    public Movie getMovie(Long id){
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    public Movie getReference(Long id){
        return movieRepository.getReferenceById(id);
    }

    public Movie putMovie(Movie movie){
        return movieRepository.save(movie);
    }
}
