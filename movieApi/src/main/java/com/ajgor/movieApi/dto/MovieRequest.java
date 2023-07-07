package com.ajgor.movieApi.dto;

import com.ajgor.movieApi.entity.Movie;
import com.ajgor.movieApi.entity.Review;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class MovieRequest {

    @NotBlank(message = "title must be passed")
    private String title;

    @NotBlank(message = "description must be passed")
    private String description;

    @NotBlank(message = "date must be passed")
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Date format should be dd/MM/yyyy")
    private String date;

    @NotEmpty(message = "genres must be passed")
    @ElementCollection
    private List<@NotBlank(message = "genre must be passed") String> genres;

    @NotBlank(message = "poster must be passed")
    @URL(message = "poster must be valid url")
    private String poster;

    private List<Review> reviews = new ArrayList<>();

    public MovieRequest(Movie movie) {
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.date = movie.getDate();
        this.genres = movie.getGenres();
        this.poster = movie.getPoster();
    }
}
