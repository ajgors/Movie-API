package com.ajgor.movieApi.service;

import com.ajgor.movieApi.dto.MovieRequest;
import com.ajgor.movieApi.dto.MovieResponse;
import com.ajgor.movieApi.entity.Movie;
import com.ajgor.movieApi.exception.MovieNotFoundException;
import com.ajgor.movieApi.repository.MovieRepository;
import com.ajgor.movieApi.specification.MovieSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Page<MovieResponse> getMovies(Specification<Movie> spec, List<String> genres, Integer page, Integer size, String sortedBy, String sortedDir) {

        Specification<Movie> cominedSpec = Specification.where(spec).and(MovieSpecification.hasAnyGenreIn(genres));

        Sort sort = sortedDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortedBy).ascending() : Sort.by(sortedBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        List<MovieResponse> movies = movieRepository.findAll(cominedSpec, pageable).stream().map(MovieResponse::new).collect(Collectors.toList());

        return new PageImpl<>(movies, pageable, movieRepository.count());
    }


    public MovieResponse getMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        return new MovieResponse(movie);
    }

    public MovieRequest putMovie(MovieRequest movie) {
        Movie movieToSave = Movie.builder().title(movie.getTitle()).description(movie.getDescription()).date(movie.getDate()).genres(movie.getGenres()).poster(movie.getPoster()).reviews(movie.getReviews()).build();

        movieRepository.save(movieToSave);
        return movie;
    }
}
