package com.POC.UserService.Services;

import com.POC.UserService.DTO.UserResponse;
import com.POC.UserService.DTO.UserResponseDTO;
import com.POC.UserService.Entities.User;

import java.util.List;

public interface UserService {

    //defined user operation

    UserResponse saveUser(User user);
    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(String userId);

    // delete user
    // update user

}
