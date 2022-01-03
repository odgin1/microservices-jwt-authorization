package io.javabrains.moviecatalogservice.resource;

import io.javabrains.moviecatalogservice.models.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalogs")
public class MovieCatalogResource {

    private final RestTemplate restTemplate;
    private final WebClient.Builder builder;

    public MovieCatalogResource(RestTemplate restTemplate, WebClient.Builder builder) {
        this.restTemplate = restTemplate;
        this.builder = builder;
    }

    @GetMapping
    public List<UserCatalog> getCatalogs() {
        List<CatalogItem> catalogUser1 = getCatalog("1");
        UserCatalog userCatalog = new UserCatalog("1", catalogUser1);
        List<CatalogItem> catalogUser2 = getCatalog("2");
        UserCatalog user2Catalog = new UserCatalog("2", catalogUser2);
        return Arrays.asList(userCatalog, user2Catalog);
    }

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        UserRating userRating = builder.build()
                .get()
                .uri("http://rating-data-service/ratingsData/users/" + userId)
                .retrieve()
                .bodyToMono(UserRating.class)
                .block();

        return userRating.getRatingList().stream().map(rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
//                    Movie movie = builder.build()
//                            .get()
//                            .uri("http://localhost:8082/movies/" + rating.getMovieId())
//                            .retrieve()
//                            .bodyToMono(Movie.class)
//                            .block();
                    return new CatalogItem(movie.getName(), "Test", rating.getRating());
                }
        ).collect(Collectors.toList());
    }
}
