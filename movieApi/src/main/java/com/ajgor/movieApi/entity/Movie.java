package com.ajgor.movieApi.entity;

import jakarta.persistence.*;
import jakarta.persistence.metamodel.StaticMetamodel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@StaticMetamodel(Movie.class)
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String date;

    @NotEmpty
    @ElementCollection
    private List<@NotBlank String> genres;

    @NotBlank
    private String poster;

    @OneToMany(mappedBy = "movie")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

}
