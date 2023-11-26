package com.POC.UserService.feignClient;

import com.POC.Entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface feignRatingService {
    //get
    @GetMapping("/ratings/users/{userId}")
    public List<Rating> getRatingByUser(@PathVariable("userId") String userId);

    //post
    @PostMapping("/ratings")
    public Rating createRating(Rating rating);

    //put
    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);

    //delete
    @GetMapping("/ratings/{ratingId}")
    public void deleteRating(@PathVariable("ratingId") String ratingId);

}
