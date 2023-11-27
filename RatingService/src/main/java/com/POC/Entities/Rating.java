package com.POC.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Document("user_ratings")
@Entity(name= "rating")
@Table
public class Rating {

    @Id
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating ;
    @Temporal(TemporalType.DATE)
    private Date date ;
    private String feedback;
}
