package com.POC.UserService.DTO;

import lombok.Data;

import javax.persistence.Column;
@Data
public class UserResponse {
    private String userId;
    private String name ;
    private String email ;
    private String about ;
    private String status ;
}
