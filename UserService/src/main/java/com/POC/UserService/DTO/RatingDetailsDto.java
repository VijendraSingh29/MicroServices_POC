package com.POC.UserService.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class RatingDetailsDto {
    private String ratingId;
    private Date date ;
    private int rating;
    private String feedback ;
}
