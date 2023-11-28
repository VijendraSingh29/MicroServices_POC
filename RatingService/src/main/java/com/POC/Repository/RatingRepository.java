package com.POC.Repository;

import com.POC.Entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> /*MongoRepository<Rating,String>*/ {
//
/*   List<Rating> findByUserId(String userId);
   List<Rating> findByHotelId(String hotelId);*/
}
