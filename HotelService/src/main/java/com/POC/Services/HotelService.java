package com.POC.Services;

import com.POC.Entities.Hotel;

import java.util.List;

public interface HotelService {

    // create Hotel

    Hotel saveHotel(Hotel hotel);

    Hotel getHotel(String id);

    List<Hotel> getAllHotels() ;

}
