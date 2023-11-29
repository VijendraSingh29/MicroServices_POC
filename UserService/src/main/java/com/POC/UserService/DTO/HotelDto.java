package com.POC.UserService.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class HotelDto {
    private String id;
    private String name ;
    private String location;
    private String about ;

    @JsonProperty(value = "My Ratings")
    List<RatingDetailsDto> ratingDtoList;
}

