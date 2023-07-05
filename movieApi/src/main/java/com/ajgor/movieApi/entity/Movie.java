package com.ajgor.movieApi.entity;

import jakarta.persistence.*;
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
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private String date;

    @NonNull
    @ElementCollection
    private List<String> genres;

    @NonNull
    private String poster;

    @OneToMany(mappedBy = "movie")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();
}
