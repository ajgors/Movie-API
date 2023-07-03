package com.ajgor.movieApi.service;

import com.ajgor.movieApi.entity.Movie;
import com.ajgor.movieApi.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isPresent())
            return movie.get();
        else
            throw new EntityNotFoundException("Movie not found");
    }

    public Movie putMovie(Movie movie){
        return movieRepository.save(movie);
    }
}
