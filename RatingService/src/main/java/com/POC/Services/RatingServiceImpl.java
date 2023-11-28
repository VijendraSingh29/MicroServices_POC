package com.POC.Services;

import com.POC.Entities.Rating;
import com.POC.Entities.RatingUserHotel;
import com.POC.Exceptions.ResourceNotFoundException;
import com.POC.Repository.RatingRepository;
import com.POC.Repository.RatingUserHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RatingServiceImpl implements RatingService{
    @Autowired
    RatingRepository ratingRepository ;


    @Autowired
    RatingUserHotelRepository ratingUserHotelRepository ;
    @Override
    public Rating create(Rating rating) {
        rating.setDate(new Date());
        rating.setRatingId(UUID.randomUUID().toString());
        createRatingUserHotelEntity(rating.getRatingId(),rating.getUserId(),rating.getHotelId());
        return ratingRepository.save(rating) ;
    }

    private void createRatingUserHotelEntity(String ratingId, String userId, String hotelId) {

        RatingUserHotel ratingUserHotel = new RatingUserHotel() ;
        ratingUserHotel.setRatingId(ratingId);
        ratingUserHotel.setUserId(userId);
        ratingUserHotel.setHotelId(hotelId);
        ratingUserHotelRepository.save(ratingUserHotel);
    }

    @Override
    public List<Rating> getRatings() {
        List<Rating> all = ratingRepository.findAll();
        for (Rating rating:all)
        {
            RatingUserHotel byId = ratingUserHotelRepository.findById(rating.getRatingId()).get();
            rating.setUserId(byId.getUserId());
            rating.setHotelId(byId.getHotelId());
        }
        return all ;
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        List<RatingUserHotel> byUserId = ratingUserHotelRepository.findByUserId(userId);
        List<Rating> ratings = new ArrayList<>();
        // find rating
        for (RatingUserHotel ratingUserHotel:byUserId)
        {
            Rating byId = ratingRepository.findById(ratingUserHotel.getRatingId()).get();
            byId.setHotelId(ratingUserHotel.getHotelId());
            byId.setUserId(ratingUserHotel.getUserId());
            ratings.add(byId);
        }
        return ratings;
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        List<RatingUserHotel> byUserId = ratingUserHotelRepository.findByHotelId(hotelId);
        List<Rating> ratings = new ArrayList<>();
        for (RatingUserHotel ratingUserHotel:byUserId)
        {
            Rating byId = ratingRepository.findById(ratingUserHotel.getRatingId()).get();
            byId.setHotelId(ratingUserHotel.getHotelId());
            byId.setUserId(ratingUserHotel.getUserId());
            ratings.add(byId);
        }
        return ratings ;
    }
}
