package com.api.user.apiuser.controller;

import com.api.user.apiuser.entities.Response;
import com.api.user.apiuser.entities.User;
import com.api.user.apiuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/user/{userId}")
    public ResponseEntity<Response> updateBook(@RequestBody User user,@PathVariable("userId") int userId){
        Response editResponse = userService.updateUser(userId,user);
        if (editResponse.isStatus()) {
            return new ResponseEntity<Response>(editResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(editResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> list = userService.getAllUsers();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }

}
