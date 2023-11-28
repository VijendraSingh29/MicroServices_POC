package com.POC.UserService.Services;

import com.POC.UserService.DTO.UserResponse;
import com.POC.UserService.Entities.User;
import com.fasterxml.jackson.databind.ser.FilterProvider;

import java.util.List;

public interface UserService {

    //defined user operation

    UserResponse saveUser(User user);
    List<User> getAllUsers();

    User getUser(String userId);

    FilterProvider getResponseFilter(String[] filters);

    // delete user
    // update user

}
