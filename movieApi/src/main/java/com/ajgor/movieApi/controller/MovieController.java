package com.ajgor.movieApi.controller;

import com.ajgor.movieApi.dto.MovieRequest;
import com.ajgor.movieApi.dto.MovieResponse;
import com.ajgor.movieApi.exception.MovieNotFoundException;
import com.ajgor.movieApi.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final String DEFAULT_PAGE_NUMBER = "0";
    private final String DEFAULT_PAGE_SIZE = "10";
    private final String DEFAULT_SORTED_BY = "id";
    private final String DEFAULT_SORTED_DIR = "asc";

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovie(@PathVariable long id) throws MovieNotFoundException {
        return ResponseEntity.ok(movieService.getMovie(id));
    }

    @GetMapping
    public ResponseEntity<Page<MovieResponse>> getMovies(
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(required = false, defaultValue = DEFAULT_SORTED_BY) String sortedBy,
            @RequestParam(required = false, defaultValue = DEFAULT_SORTED_DIR) String sortedDir
    ) {
        return ResponseEntity.ok(movieService.getMovies(page, size, sortedBy, sortedDir));
    }

    @PostMapping
    public ResponseEntity<MovieRequest> putMovie(@RequestBody @Valid MovieRequest movie) {
        return new ResponseEntity<>(movieService.putMovie(movie), HttpStatus.CREATED);
    }
}
