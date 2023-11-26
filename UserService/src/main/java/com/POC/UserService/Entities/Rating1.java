package com.POC.UserService.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rating1 {

    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback ;

}
