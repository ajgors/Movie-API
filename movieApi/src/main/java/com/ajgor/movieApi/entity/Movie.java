package com.ajgor.movieApi.entity;

import com.ajgor.movieApi.dto.MovieRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.springframework.lang.NonNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
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
