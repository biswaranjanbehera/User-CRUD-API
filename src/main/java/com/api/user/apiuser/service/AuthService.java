package com.api.user.apiuser.service;

import com.api.user.apiuser.entities.LoginRequest;
import com.api.user.apiuser.entities.Response;
import com.api.user.apiuser.entities.User;
import com.api.user.apiuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    User loggedInUser;

    public Response addUser(User user){
        User newUser = userRepository.findByEmail(user.getEmail());
        Response response = new Response();
        if (newUser != null) {
            response.setStatus(false);
            response.setMessage("User already exist with this email: " + user.getEmail());
        }else{
            User result = userRepository.save(user);
            response.setStatus(true);
            response.setMessage("Signup success");
            result.setPassword("password is private");
            response.setPayload(result);
        }
        return response;
    }

    public Response loginUser(LoginRequest loginRequest){
        Response loginResponse = new Response();
        if(loggedInUser==null) {
            User user = userRepository.findByEmail(loginRequest.getEmail());

            if (user == null) {
                loginResponse.setStatus(false);
                loginResponse.setMessage("user does not found with this email: " + loginRequest.getEmail());

            } else {
                if (loginRequest.getPassword().equals(user.getPassword())) {
                    loggedInUser = user;
                    loginResponse.setStatus(true);
                    loginResponse.setMessage("Login successful for user : " + user.getEmail());
                    user.setPassword("password is private");
                    loginResponse.setPayload(user);
                } else {
                    loginResponse.setStatus(false);
                    loginResponse.setMessage("invalid password: " + loginRequest.getPassword());
                }
            }
            return loginResponse;
        }else{
            String msg= "You are already logged in";
            loginResponse.setStatus(false);
            loginResponse.setMessage(msg);
            loginResponse.setPayload("Your email id is: "+loggedInUser.getEmail());
            return loginResponse;
        }
    }

    public Response logoutUser(int userId){
        Response logoutResponse = new Response();
        if(loggedInUser==null){
            String exceptionMessage = "User with userId " + userId + " is not logged in.";
            logoutResponse.setMessage(exceptionMessage);
            logoutResponse.setStatus(false);
        }
        else if (loggedInUser.getId()==userId) {
            loggedInUser = null;
            logoutResponse.setStatus(true);
            logoutResponse.setMessage("Logout successful");
        } else {
            logoutResponse.setStatus(false);
            logoutResponse.setMessage("You are trying to access someone's else account which is not allowed");
        }
        return logoutResponse;
    }
}
