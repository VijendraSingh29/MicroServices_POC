package com.POC.UserService.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class RatingDto {
    private String ratingId;
    private String userId ;
    private String hotelId;
    private Date date ;
    private int rating;
    private String feedback ;
    /*
    @JsonProperty("Hotel_Details")
    private HotelDto hotelDto ;*/
}
