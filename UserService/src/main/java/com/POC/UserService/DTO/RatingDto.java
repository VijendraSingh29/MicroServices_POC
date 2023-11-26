package com.POC.UserService.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RatingDto {
    private String ratingId;
    private String hotelId;
    private int rating;
    private String feedback ;

}
