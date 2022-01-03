package io.javabrains.ratingdataservice.resources;

import io.javabrains.ratingdataservice.models.Rating;
import io.javabrains.ratingdataservice.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsData")
public class RatingResource {

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId) {
        return new Rating(movieId, 4);
    }

    @GetMapping("users/{userId}")
    public UserRating getRatings(@PathVariable String userId) {
        List<Rating> user1Ratings = Arrays.asList(
                new Rating("0001", 4),
                new Rating("0002", 10),
                new Rating("0003", 7)
        );
        List<Rating> user2Ratings = Arrays.asList(
                new Rating("0004", 6),
                new Rating("0005", 7),
                new Rating("0006", 9)
        );
        UserRating userRating = new UserRating();
        if (userId.equals("1")) {
            userRating.setUserId("1");
            userRating.setRatingList(user1Ratings);
        } else {
            userRating.setUserId(userId);
            userRating.setRatingList(user2Ratings);
        }
        return userRating;
    }

}
