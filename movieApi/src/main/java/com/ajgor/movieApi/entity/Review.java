package com.ajgor.movieApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue
    private Long id;
    private String author;

    @Column(columnDefinition = "integer CHECK (rating >= 0 AND rating <= 10)")
    private double rating;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Movie movie;
}
