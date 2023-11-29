package com.POC.UserService.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO  {

    private String userId;

    @JsonProperty(value = "UserName")
    private String name ;

    @JsonProperty(value = "emailId")
    private String email ;

    @JsonProperty(value = "Info about User")
    private String about ;

    @JsonProperty(value = "My All Ratings")
    private List<HotelDto> rating ;
}
