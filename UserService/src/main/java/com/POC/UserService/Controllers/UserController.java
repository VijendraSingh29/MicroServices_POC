package com.POC.UserService.Controllers;

import com.POC.UserService.DTO.UserResponse;
import com.POC.UserService.Entities.User;
import com.POC.UserService.Services.UserService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
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

    @PostMapping("/saveUser/")
    public MappingJacksonValue saveUsers(@RequestBody User user)
    {
        UserResponse userResponse = userService.saveUser(user);
        String [] filters = {"UserName","My All Ratings"};
        FilterProvider filter= userService.getResponseFilter(filters);
        MappingJacksonValue value = new MappingJacksonValue(userResponse);
        value.setFilters(filter);
        return value;
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId)
    {
        User user = userService.getUser(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }
}
