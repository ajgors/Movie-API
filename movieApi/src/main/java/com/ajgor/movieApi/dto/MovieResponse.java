package com.ajgor.movieApi.dto;

import com.ajgor.movieApi.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class MovieResponse {

    private Long id;
    private String title;
    private String description;
    private String date;
    private List<String> genres;
    private String poster;

    public MovieResponse(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.date = movie.getDate();
        this.genres = movie.getGenres();
        this.poster = movie.getPoster();
    }
}
