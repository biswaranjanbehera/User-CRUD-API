package com.api.user.apiuser.controller;

import com.api.user.apiuser.entities.LoginRequest;
import com.api.user.apiuser.entities.Response;
import com.api.user.apiuser.entities.User;
import com.api.user.apiuser.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<Response> signUp(@RequestBody User user) {
        try {
            Response signupResponse = authService.addUser(user);
            if (signupResponse.isStatus()) {
                return new ResponseEntity<Response>(signupResponse, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<Response>(signupResponse, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            Response signupResponse = null;
            return new ResponseEntity<Response>(signupResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) {
        try {
            Response loginResponse = authService.loginUser(loginRequest);
            if (loginResponse.isStatus()) {
                return new ResponseEntity<Response>(loginResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<Response>(loginResponse, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            Response loginResponse = null;
            return new ResponseEntity<Response>(loginResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/logout/{id}")
    public ResponseEntity<Response> logout(@PathVariable("id") int id) {
        return new ResponseEntity<Response>(authService.logoutUser(id), HttpStatus.OK);
    }
}
