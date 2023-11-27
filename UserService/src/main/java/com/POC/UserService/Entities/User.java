package com.POC.UserService.Entities;

import com.POC.UserService.DTO.RatingDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "NAME", length = 20)
    private String name ;

    private String email ;

    private String about ;

    @Transient
    private List<RatingDto> rating ;

}
