package com.ajgor.movieApi.specification;

import com.ajgor.movieApi.entity.Movie;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class MovieSpecification {

    public static Specification<Movie> hasAnyGenreIn(List<String> genres) {
        return (root, query, builder) -> {
            if (genres == null || genres.isEmpty()) return builder.conjunction();

            final Path<List<String>> genresPath = root.get("genres");
            Predicate[] predicates = genres.stream()
                    .map(genre -> builder.isMember(genre, genresPath))
                    .toArray(Predicate[]::new);
            return builder.or(predicates);
        };
    }

    public static Specification<Movie> titleContains(String title) {
        return (root, query, builder) -> {
            if (title == null || title.isEmpty()) return builder.conjunction();

            final Path<String> titlePath = root.get("title");
            return builder.like(builder.lower(titlePath), "%" + title.toLowerCase() + "%");
        };
    }

}