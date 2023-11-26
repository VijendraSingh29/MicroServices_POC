package com.POC.Services;

import com.POC.Entities.Hotel;
import com.POC.Exceptions.ResourceNotFoundException;
import com.POC.Repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    HotelRepository hotelRepository ;
    @Override
    public Hotel saveHotel(Hotel hotel) {
          return  hotelRepository.save(hotel) ;
    }

    @Override
    public Hotel getHotel(String id) {
        return hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("hotel is not found in the database for this id : " + id));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
