package com.ajgor.movieApi.specification;

import com.ajgor.movieApi.entity.Review;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;

public class ReviewSpecification {

    public static Specification<Review> greaterThan(Double greaterThan) {
        return (root, query, builder) -> {
            if (greaterThan == null) return builder.conjunction();

            final Path<Double> ratingPath = root.get("rating");
            return builder.greaterThanOrEqualTo(ratingPath, greaterThan);
        };
    }

    public static Specification<Review> movieId(Long movieId) {
        return (root, query, builder) -> {
            if (movieId == null) return builder.conjunction();

            final Path<Long> movieIdPath = root.get("movie").get("id");
            return builder.equal(movieIdPath, movieId);
        };
    }
}
