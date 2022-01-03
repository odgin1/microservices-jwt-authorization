package io.javabrains.movieinfoservice.resources;

import io.javabrains.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable String movieId) {
        List<Movie> movies = Arrays.asList(
                new Movie("0001", "Transformer"),
                new Movie("0002", "Back to future"),
                new Movie("0003", "Vasea is back"),
                new Movie("0004", "Maria Mirabela"),
                new Movie("0005", "Bad boys"),
                new Movie("0006", "Bad Ass")
        );

        return movies.stream()
                .filter(movie -> movie.getMovieId().equals(movieId))
                .findAny()
                .orElseThrow(() -> new RuntimeException("No such movie with id: " + movieId));
    }
}
