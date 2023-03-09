package com.api.user.apiuser.service;

import com.api.user.apiuser.entities.Response;
import com.api.user.apiuser.entities.User;
import com.api.user.apiuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthService authService;

    public Response updateUser(int id, User user) {
        if (authService.loggedInUser != null) {
            if(authService.loggedInUser.getId()==id){
                User updateUser = userRepository.findById(id);
                Response updateResponse = new Response();
                if (updateUser == null) {
                    updateResponse.setStatus(false);
                    updateResponse.setMessage("user does not found with this id: ");

                } else {
                    if (user.getName() != null)
                        updateUser.setName(user.getName());
                    if (user.getEmail() != null)
                        updateUser.setEmail(user.getEmail());
                    if (user.getPhone_number() != null)
                        updateUser.setPhone_number(user.getPhone_number());
                    if (user.getPassword() != null) {
                        updateUser.setPassword(user.getPassword());
                    }
                    if (user.getAge()>0) {
                        updateUser.setAge(user.getAge());
                    }
                    userRepository.save(updateUser);

                    updateResponse.setStatus(true);
                    updateResponse.setMessage("Update Successful");
                }
                return updateResponse;

            }else{
                Response updateResponse = new Response();
                updateResponse.setPayload("You are trying to edit some other profile which is not appropriate");
                updateResponse.setStatus(false);
                return updateResponse;
            }
        } else {
            String exceptionMessage = "You are not logged in.";
            Response updateResponse = new Response();
            updateResponse.setStatus(false);
            updateResponse.setMessage(exceptionMessage);
            return updateResponse;
        }
    }

    public List<User> getAllUsers(){
        List<User> list = (List<User>)this.userRepository.findAll();
        List<User> answerList = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            list.get(i).setPassword("Password is private");
            answerList.add(list.get(i));
        }
        return answerList;
    }


}
