package com.POC.UserService.Services;

import com.POC.UserService.Entities.User;

import java.util.List;

public interface UserService {

    //defined user operation

    User  saveUser(User user);
    List<User> getAllUsers();

    User getUser(String userId);

    // delete user
    // update user

}
