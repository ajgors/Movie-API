package com.ajgor.movieApi.service;

import com.ajgor.movieApi.dto.MovieRequest;
import com.ajgor.movieApi.dto.MovieResponse;
import com.ajgor.movieApi.entity.Movie;
import com.ajgor.movieApi.exception.MovieNotFoundException;
import com.ajgor.movieApi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Page<MovieResponse> getMovies(Specification<Movie> spec, Pageable pageable) {
        return movieRepository.findAll(spec, pageable).map(MovieResponse::new);
    }

    public MovieResponse getMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        return new MovieResponse(movie);
    }

    public MovieRequest putMovie(MovieRequest movie) {
        Movie movieToSave = Movie.builder()
                .title(movie.getTitle())
                .description(movie.getDescription())
                .date(movie.getDate())
                .genres(movie.getGenres())
                .poster(movie.getPoster())
                .build();

        movieRepository.save(movieToSave);
        return movie;
    }
}
