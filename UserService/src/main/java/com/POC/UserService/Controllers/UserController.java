package com.POC.UserService.Controllers;

import com.POC.UserService.DTO.UserResponse;
import com.POC.UserService.DTO.UserResponseDTO;
import com.POC.UserService.Entities.User;
import com.POC.UserService.Services.UserService;
import com.POC.UserService.Services.Utils.UserUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService ;

    @Autowired
    UserUtils userUtils ;

    @PostMapping("/saveUser/")
    /*@FieldFilterSetting(className = UserResponse.class, fields = {"id", "password", "secretKey"})*/
    public MappingJacksonValue saveUsers(@RequestBody User user)
    {
        UserResponse userResponse = userService.saveUser(user);
        // Apply Filters in ResponseObject, responseBody will not display these filter information.
        String [] filters = {"UserName","My All Ratings"};
        MappingJacksonValue responseFilter = userUtils.getResponseFilter(filters, userResponse);
        return responseFilter ;
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String userId)
    {
        UserResponseDTO user = userService.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers()
    {
        List<UserResponseDTO> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }
}
