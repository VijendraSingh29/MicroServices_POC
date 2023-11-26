package com.POC.Services;

import com.POC.Entities.Rating;

import java.util.List;

public interface RatingService {

    //
    Rating create(Rating rating);

    List<Rating> getRatings() ;

    List<Rating> getRatingByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId) ;
}
