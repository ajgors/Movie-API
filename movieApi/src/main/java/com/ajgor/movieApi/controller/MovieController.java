package com.ajgor.movieApi.controller;

import com.ajgor.movieApi.exception.MovieNotFoundException;
import com.ajgor.movieApi.entity.Movie;
import com.ajgor.movieApi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable long id) throws MovieNotFoundException {
        return ResponseEntity.ok(movieService.getMovie(id));
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        return ResponseEntity.ok(movieService.getMovies());
    }


    @PostMapping
    public ResponseEntity<Movie> putMovie(@RequestBody Movie movie){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(movieService.putMovie(movie).getId())
                .toUri();
        return ResponseEntity.created(uri).body(movieService.putMovie(movie));
    }
}
