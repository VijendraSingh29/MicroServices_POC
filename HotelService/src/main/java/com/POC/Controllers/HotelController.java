package com.POC.Controllers;

import com.POC.Entities.Hotel;
import com.POC.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    HotelService hotelService ;

    @PostMapping("/saveHotel")
    ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
    {
        hotel.setId(UUID.randomUUID().toString());
        hotelService.saveHotel(hotel) ;
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    @GetMapping("/getHotelById/{id}")
    ResponseEntity<Hotel> getHotelById(@PathVariable String id)
    {
        Hotel hotel = hotelService.getHotel(id);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @GetMapping("/getAllHotels")
    ResponseEntity<List<Hotel>> getHotelById()
    {
        List<Hotel> hotel = hotelService.getAllHotels();
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }


}
