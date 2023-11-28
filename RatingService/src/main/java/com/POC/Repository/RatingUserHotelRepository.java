package com.POC.Repository;

import com.POC.Entities.RatingUserHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingUserHotelRepository extends JpaRepository<RatingUserHotel,String> {
   List<RatingUserHotel> findByUserId(String userId);
   List<RatingUserHotel> findByHotelId(String hotelId);
}
