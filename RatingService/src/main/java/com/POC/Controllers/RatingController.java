package com.POC.Controllers;

import com.POC.Entities.Rating;
import com.POC.Services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    RatingService ratingService ;

    @PostMapping("/createRating")
    public ResponseEntity<Rating> create(@RequestBody Rating rating)
    {
        ratingService.create(rating);
        return new ResponseEntity<>(rating, HttpStatus.CREATED);

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Rating>> getRatings()
    {
        List<Rating> ratings = ratingService.getRatings();
        return new ResponseEntity<>(ratings,HttpStatus.OK);

    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId)
    {
        List<Rating> ratings = ratingService.getRatingByUserId(userId);
        return new ResponseEntity<>(ratings,HttpStatus.OK);

    }
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId)
    {
        List<Rating> ratings = ratingService.getRatingByHotelId(hotelId);
        return new ResponseEntity<>(ratings,HttpStatus.OK);

    }




}
