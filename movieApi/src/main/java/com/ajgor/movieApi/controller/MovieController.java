package com.ajgor.movieApi.controller;

import com.ajgor.movieApi.dto.MovieRequest;
import com.ajgor.movieApi.dto.MovieResponse;
import com.ajgor.movieApi.entity.Movie;
import com.ajgor.movieApi.exception.MovieNotFoundException;
import com.ajgor.movieApi.service.MovieService;
import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/genres")
    public String getGenres() {
        return "akcja, Thriller, Komedia, Horror, Sci-Fiasd";
    }
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovie(@PathVariable long id) throws MovieNotFoundException {
        return ResponseEntity.ok(movieService.getMovie(id));
    }

    //teste
    //    http://localhost:8080/movies?filter=genres in %5B'akcja', 'Thriller'%5D
    @GetMapping()
    public ResponseEntity<Page<MovieResponse>> getMovies(
            @Filter Specification<Movie> spec,
            Pageable pageable
    ) {
        return ResponseEntity.ok(movieService.getMovies(spec, pageable));
    }

    @PostMapping
    public ResponseEntity<MovieRequest> putMovie(@RequestBody @Valid MovieRequest movie) {
        return new ResponseEntity<>(movieService.putMovie(movie), HttpStatus.CREATED);
    }
}
