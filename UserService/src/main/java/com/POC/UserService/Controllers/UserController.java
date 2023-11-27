package com.POC.UserService.Controllers;

import com.POC.UserService.Entities.User;
import com.POC.UserService.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService ;

    @PostMapping("/saveUser/")
    public ResponseEntity<User> saveUsers(@RequestBody User user)
    {
       userService.saveUser(user);
       return new ResponseEntity<>(user, HttpStatus.CREATED);
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
